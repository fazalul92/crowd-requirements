package edu.ncsu.mas.platys.crowdre.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ncsu.mas.platys.crowdre.form.PresurveyResponseForm;
import edu.ncsu.mas.platys.crowdre.form.PersonalityResponseForm;
import edu.ncsu.mas.platys.crowdre.form.CreativityResponseForm;
import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;
import edu.ncsu.mas.platys.crowdre.model.PersonalityQuestion;
import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;
import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;
import edu.ncsu.mas.platys.crowdre.model.User;
import edu.ncsu.mas.platys.crowdre.model.PresurveyResponse;
import edu.ncsu.mas.platys.crowdre.model.PersonalityResponse;
import edu.ncsu.mas.platys.crowdre.model.CreativityResponse;
import edu.ncsu.mas.platys.crowdre.service.PresurveyQuestionService;
import edu.ncsu.mas.platys.crowdre.service.PersonalityQuestionService;
import edu.ncsu.mas.platys.crowdre.service.CreativityQuestionService;
import edu.ncsu.mas.platys.crowdre.service.PresurveyResponseService;
import edu.ncsu.mas.platys.crowdre.service.PersonalityResponseService;
import edu.ncsu.mas.platys.crowdre.service.CreativityResponseService;
import edu.ncsu.mas.platys.crowdre.service.RequirementResponseService;
import edu.ncsu.mas.platys.crowdre.service.UserService;

@Controller
@RequestMapping("/")
@PropertySource("classpath:application.properties")
public class AppController {

  @Autowired
  MessageSource messageSource;
  
  @Autowired
  private Environment env;

  @Autowired
  UserService userService;

  @Autowired
  PresurveyQuestionService presurveyQuestionService;

  @Autowired
  PresurveyResponseService presurveyResponseService;

  @Autowired
  PersonalityQuestionService personalityQuestionService;

  @Autowired
  PersonalityResponseService personalityResponseService;
  
  @Autowired
  CreativityQuestionService creativityQuestionService;

  @Autowired
  CreativityResponseService creativityResponseService;
  
  @Autowired
  RequirementResponseService requirementResponseService;
  
  private static final String PAGE_SIGNIN = "signin";
  private static final String PAGE_SIGNIN_FAILURE = "signin_failure";
  private static final String PAGE_REDIRECT_SIGNIN_FAILURE = "redirect:signin_failure";
  
  private static final String PAGE_PRESURVEY = "presurvey";
  private static final String PAGE_REDIRECT_PRESURVEY = "redirect:presurvey";
  
  private static final String PAGE_PERSONALITY = "personality";
  private static final String PAGE_REDIRECT_PERSONALITY = "redirect:personality";
  
  private static final String PAGE_CREATIVITY = "creativity";
  private static final String PAGE_REDIRECT_CREATIVITY = "redirect:creativity";
  
  //private static final String PAGE_REDIRECT_PRESURVEY2 = "redirect:presurvey2";
    
  private static final String PAGE_REQUIREMENTS_PHASE1 = "requirements_phase1";
  private static final String PAGE_REQUIREMENTS_PHASE2 = "requirements_phase2";
  
  private static final String ATTR_SIGN_FAILURE_REASON = "signinFailureReason";
  private static final String ATTR_USER = "user";
  private static final String ATTR_PRESURVEY_QUESTIONS = "presurveyQuestions";
  private static final String ATTR_PRESURVEY_RESPONSE_FORM = "presurveyResponseForm";
  private static final String ATTR_PERSONALITY_QUESTIONS = "personalityQuestions";
  private static final String ATTR_PERSONALITY_RESPONSE_FORM = "personalityResponseForm";
  private static final String ATTR_CREATIVITY_QUESTIONS = "creativityQuestions";
  private static final String ATTR_CREATIVITY_RESPONSE_FORM = "creativityResponseForm";
  
  private static final String ATTR_REQUIREMENT_RESPONSE = "requirementResponse";
  private static final String ATTR_PREVIOUS_REQUIREMENT_RESPONSES = "previousRequirementResponses";
  
  private static final int MTURK_ID_VALID = 0;
  private static final int MTURK_ID_INVALID = 1;
  private static final int MTURK_ID_COMPLETED = 2;
    
  @RequestMapping(value = { "/", "/" + PAGE_SIGNIN }, method = RequestMethod.GET)
  public String showSignIn(ModelMap model) {
    model.addAttribute(ATTR_USER, new User());
    return PAGE_SIGNIN;
  }

  @RequestMapping(value = { "/", "/" + PAGE_SIGNIN }, method = RequestMethod.POST)
  public String processSignIn(@ModelAttribute(ATTR_USER) User user,
      final RedirectAttributes redirectAttributes) {
    
    redirectAttributes.addFlashAttribute(ATTR_USER, user);
    
    switch (isMturkIDValid(user.getMturkId())) {
    case MTURK_ID_VALID:
      user.setCreatedAt(LocalDateTime.now());
      userService.saveResponse(user);
      return PAGE_REDIRECT_PRESURVEY;
      
    case MTURK_ID_COMPLETED:
      redirectAttributes.addFlashAttribute(ATTR_SIGN_FAILURE_REASON,
          "You can only submit one response to this survey; "
          + "you have already submitted a response to this survey in this or a previous batch.");
      return PAGE_REDIRECT_SIGNIN_FAILURE;
      
    case MTURK_ID_INVALID:
      redirectAttributes.addFlashAttribute(ATTR_SIGN_FAILURE_REASON, "Your MTurk ID is invalid.");
      return PAGE_REDIRECT_SIGNIN_FAILURE;
      
    default:
      redirectAttributes.addFlashAttribute(ATTR_SIGN_FAILURE_REASON, "An unknown error occurred.");
      return PAGE_REDIRECT_SIGNIN_FAILURE;
    }
  }

  @RequestMapping(value = { "/" + PAGE_SIGNIN_FAILURE }, method = RequestMethod.GET)
  public String showSigninFailure(@ModelAttribute(ATTR_USER) User user,
      @ModelAttribute(ATTR_SIGN_FAILURE_REASON) String signinFailureReason, BindingResult result,
      ModelMap model) {
    model.addAttribute(ATTR_USER, user);
    model.addAttribute(ATTR_SIGN_FAILURE_REASON, signinFailureReason);
    return PAGE_SIGNIN_FAILURE;
  }

  @RequestMapping(value = { "/" + PAGE_PRESURVEY }, method = RequestMethod.GET)
  public String showPresurvey(@ModelAttribute(ATTR_USER) User user, BindingResult result,
      ModelMap model) {
    int numQuestions = (int) presurveyQuestionService.getCount();
    PresurveyQuestion[] presurveyQuestions = new PresurveyQuestion[numQuestions];
    PresurveyResponse[] presurveyResponses = new PresurveyResponse[numQuestions];
    
    for (int i = 0; i < numQuestions; i++) {
      presurveyQuestions[i] = presurveyQuestionService.findById(i + 1);
      presurveyResponses[i] = new PresurveyResponse();
      presurveyResponses[i].setUserId(user.getId());
      presurveyResponses[i].setPresurveyQuestionId(presurveyQuestions[i].getId());
    }
    
    PresurveyResponseForm presurveyResponseForm = new PresurveyResponseForm();
    presurveyResponseForm.setPresurveyResponses(presurveyResponses);
    
    model.addAttribute(ATTR_PRESURVEY_QUESTIONS, presurveyQuestions);
    model.addAttribute(ATTR_PRESURVEY_RESPONSE_FORM, presurveyResponseForm);
    
    return PAGE_PRESURVEY;
  }
  
  
  @RequestMapping(value = { "/" + PAGE_PRESURVEY }, method = RequestMethod.POST)
  public String processPresurveyResponse(
      @ModelAttribute(ATTR_PRESURVEY_RESPONSE_FORM) PresurveyResponseForm presurveyResponseForm,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {
    if (isTurkerPresurveyResponseFormValid(presurveyResponseForm, result, model)) {
      PresurveyResponse[] presurveyResponses = presurveyResponseForm.getPresurveyResponses();
      for (int i = 0; i < presurveyResponses.length; i++) {
        presurveyResponses[i].setCreatedAt(LocalDateTime.now());
        presurveyResponseService.saveResponse(presurveyResponses[i]);
      }
      
      User user = userService.findById(presurveyResponses[0].getUserId()); 
      redirectAttributes.addFlashAttribute(ATTR_USER, user);
      
      return PAGE_REDIRECT_PERSONALITY;
    } else {
      // Page has errors
      int numQuestions = (int) presurveyQuestionService.getCount();
      PresurveyQuestion[] presurveyQuestions = new PresurveyQuestion[numQuestions];
      for (int i = 0; i < numQuestions; i++) {
        presurveyQuestions[i] = presurveyQuestionService.findById(i + 1);
      }
      model.addAttribute(ATTR_PRESURVEY_QUESTIONS, presurveyQuestions);
      model.addAttribute(ATTR_PRESURVEY_RESPONSE_FORM, presurveyResponseForm);
      return PAGE_PRESURVEY;
    }
  }

  @RequestMapping(value = { "/" + PAGE_PERSONALITY }, method = RequestMethod.GET)
  public String showPersonality(@ModelAttribute(ATTR_USER) User user, BindingResult result,
      ModelMap model) {
    
    int numQuestions = (int) personalityQuestionService.getCount();
    PersonalityQuestion[] personalityQuestions = new PersonalityQuestion[numQuestions];
    PersonalityResponse[] personalityResponses = new PersonalityResponse[numQuestions];
    
    for (int i = 0; i < numQuestions; i++) {
      personalityQuestions[i] = personalityQuestionService.findById(i + 1);
      personalityResponses[i] = new PersonalityResponse();
      personalityResponses[i].setUserId(user.getId());
      personalityResponses[i].setPersonalityQuestionId(personalityQuestions[i].getId());
    }
    
    PersonalityResponseForm personalityResponseForm = new PersonalityResponseForm();
    personalityResponseForm.setPersonalityResponses(personalityResponses);
    
    model.addAttribute(ATTR_PERSONALITY_QUESTIONS, personalityQuestions);
    model.addAttribute(ATTR_PERSONALITY_RESPONSE_FORM, personalityResponseForm);
    
    return PAGE_PERSONALITY;
  }
  
  
  @RequestMapping(value = { "/" + PAGE_PERSONALITY }, method = RequestMethod.POST)
  public String processPersonalityResponse(
      @ModelAttribute(ATTR_PERSONALITY_RESPONSE_FORM) PersonalityResponseForm personalityResponseForm,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {
    if (isTurkerPersonalityResponseFormValid(personalityResponseForm, result, model)) {
      PersonalityResponse[] personalityResponses = personalityResponseForm.getPersonalityResponses();
      for (int i = 0; i < personalityResponses.length; i++) {
        personalityResponses[i].setCreatedAt(LocalDateTime.now());
        personalityResponseService.saveResponse(personalityResponses[i]);
      }

      User user = userService.findById(personalityResponses[0].getUserId()); 
      redirectAttributes.addFlashAttribute(ATTR_USER, user);

      return PAGE_REDIRECT_CREATIVITY;
    } else {
      // Page has errors
      int numQuestions = (int) personalityQuestionService.getCount();
      PersonalityQuestion[] personalityQuestions = new PersonalityQuestion[numQuestions];
      for (int i = 0; i < numQuestions; i++) {
        personalityQuestions[i] = personalityQuestionService.findById(i + 1);
      }
      model.addAttribute(ATTR_PERSONALITY_QUESTIONS, personalityQuestions);
      model.addAttribute(ATTR_PERSONALITY_RESPONSE_FORM, personalityResponseForm);
      return PAGE_PERSONALITY;
    }
  }

  @RequestMapping(value = { "/" + PAGE_CREATIVITY }, method = RequestMethod.GET)
  public String showCreativity(@ModelAttribute(ATTR_USER) User user, BindingResult result,
      ModelMap model) {
    
    int numQuestions = (int) creativityQuestionService.getCount();
    CreativityQuestion[] creativityQuestions = new CreativityQuestion[numQuestions];
    CreativityResponse[] creativityResponses = new CreativityResponse[numQuestions];
    
    for (int i = 0; i < numQuestions; i++) {
      creativityQuestions[i] = creativityQuestionService.findById(i + 1);
      creativityResponses[i] = new CreativityResponse();
      creativityResponses[i].setUserId(user.getId());
      creativityResponses[i].setCreativityQuestionId(creativityQuestions[i].getId());
    }
    
    CreativityResponseForm creativityResponseForm = new CreativityResponseForm();
    creativityResponseForm.setCreativityResponses(creativityResponses);
    
    model.addAttribute(ATTR_CREATIVITY_QUESTIONS, creativityQuestions);
    model.addAttribute(ATTR_CREATIVITY_RESPONSE_FORM, creativityResponseForm);
    
    return PAGE_CREATIVITY;
  }
  
  
  @RequestMapping(value = { "/" + PAGE_CREATIVITY }, method = RequestMethod.POST)
  public String processCreativityResponse(
      @ModelAttribute(ATTR_CREATIVITY_RESPONSE_FORM) CreativityResponseForm creativityResponseForm,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {
    if (isTurkerCreativityResponseFormValid(creativityResponseForm, result, model)) {
      CreativityResponse[] creativityResponses = creativityResponseForm.getCreativityResponses();
      for (int i = 0; i < creativityResponses.length; i++) {
        creativityResponses[i].setCreatedAt(LocalDateTime.now());
        creativityResponseService.saveResponse(creativityResponses[i]);
      }
      return PAGE_REQUIREMENTS_PHASE1;
    } else {
      // Page has errors
      int numQuestions = (int) creativityQuestionService.getCount();
      CreativityQuestion[] creativityQuestions = new CreativityQuestion[numQuestions];
      for (int i = 0; i < numQuestions; i++) {
        creativityQuestions[i] = creativityQuestionService.findById(i + 1);
      }
      model.addAttribute(ATTR_CREATIVITY_QUESTIONS, creativityQuestions);
      model.addAttribute(ATTR_CREATIVITY_RESPONSE_FORM, creativityResponseForm);
      return PAGE_CREATIVITY;
    }
  }

  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE1 }, method = RequestMethod.GET)
  public String showRequirementsPhase1(ModelMap model) {
    User user = userService.findById(1); // TODO Remove
    model.addAttribute(ATTR_USER, user);

    Map<String, List<RequirementResponse>> previousRequirementResponses = requirementResponseService
        .findByUserIdAndGroupByDomain(user.getId());
    model.addAttribute(ATTR_PREVIOUS_REQUIREMENT_RESPONSES, previousRequirementResponses);
    
    RequirementResponse requirementResponse = new RequirementResponse();
    requirementResponse.setUserId(user.getId());
    model.addAttribute(ATTR_REQUIREMENT_RESPONSE, requirementResponse);

    return PAGE_REQUIREMENTS_PHASE1;
  }
  
  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE1 }, method = RequestMethod.POST)
  public String processRequirementsPhase1Response(
      @ModelAttribute(ATTR_REQUIREMENT_RESPONSE) RequirementResponse requirementResponse,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {
    if (isRequirementResponseValid(requirementResponse, result, model)) {
      requirementResponse.setCreatedAt(LocalDateTime.now());
      requirementResponseService.saveResponse(requirementResponse);
      return PAGE_REQUIREMENTS_PHASE2;
    } else {
      // Page has errors
      model.addAttribute(ATTR_REQUIREMENT_RESPONSE, requirementResponse);
      return PAGE_CREATIVITY;
    }
  }
  
  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE2 }, method = RequestMethod.GET)
  public String showRequirementsPhase2(ModelMap model) {
    model.addAttribute(ATTR_USER, new User());    
    return PAGE_REQUIREMENTS_PHASE2;
  }
  
  /*
   * Could not find much information on the Mturk ID specification. The length 3
   * has been chosen intuitively.
   */
  private int isMturkIDValid(String mturkID) {
    if (mturkID == null || mturkID.trim().length() <= 3) {
      return MTURK_ID_INVALID;
    } else if (mturkID.equals("pmuruka") || mturkID.equals("najmeri")) { // Exceptions for testing
      return MTURK_ID_VALID;
    }
    return MTURK_ID_VALID;
  }

  private boolean isTurkerPresurveyResponseFormValid(PresurveyResponseForm presurveyResponseForm,
      BindingResult result, ModelMap model) {
    PresurveyResponse[] presurveyResponses = presurveyResponseForm.getPresurveyResponses();
    boolean returnValue = true;
    for (int i = 0; i < presurveyResponses.length; i++) {
      if (presurveyResponses[i].getDescription() == null) {
        FieldError error = new FieldError(ATTR_PRESURVEY_RESPONSE_FORM, "presurveyResponses[" + i
            + "].description", messageSource.getMessage("mandatory.answer", null,
            Locale.getDefault()));
        result.addError(error);
        returnValue = false;
      }
    }
    return returnValue;
  }
  
  private boolean isTurkerPersonalityResponseFormValid(
      PersonalityResponseForm personalityResponseForm, BindingResult result, ModelMap model) {
    PersonalityResponse[] personalityResponses = personalityResponseForm.getPersonalityResponses();
    boolean returnValue = true;
    for (int i = 0; i < personalityResponses.length; i++) {
      if (personalityResponses[i].getDescription() == null) {
        FieldError error = new FieldError(ATTR_PERSONALITY_RESPONSE_FORM, "personalityResponses["
            + i + "].description", messageSource.getMessage("mandatory.answer", null,
            Locale.getDefault()));
        result.addError(error);
        returnValue = false;
      }
    }
    return returnValue;
  }
  
  private boolean isTurkerCreativityResponseFormValid(
      CreativityResponseForm creativityResponseForm, BindingResult result, ModelMap model) {
    CreativityResponse[] creativityResponses = creativityResponseForm.getCreativityResponses();
    boolean returnValue = true;
    for (int i = 0; i < creativityResponses.length; i++) {
      if (creativityResponses[i].getDescription() == null) {
        FieldError error = new FieldError(ATTR_CREATIVITY_RESPONSE_FORM, "creativityResponses[" + i
            + "].description", messageSource.getMessage("mandatory.answer", null,
            Locale.getDefault()));
        result.addError(error);
        returnValue = false;
      }
    }
    return returnValue;
  }
  
  private boolean isRequirementResponseValid(RequirementResponse requirementResponse,
      BindingResult result, ModelMap model) {
    boolean returnValue = true;
    if (requirementResponse.getDescription() == null
        || requirementResponse.getDescription().trim().length() == 0) {
      returnValue = false;
      FieldError error = new FieldError(ATTR_REQUIREMENT_RESPONSE,
          "requirementResponse.description", messageSource.getMessage("mandatory.answer", null,
              Locale.getDefault()));
      result.addError(error);
    }
    if (requirementResponse.equals("--Select--")) {
      FieldError error = new FieldError(ATTR_REQUIREMENT_RESPONSE,
          "requirementResponse.applicationDomain", messageSource.getMessage("mandatory.select",
              null, Locale.getDefault()));
      result.addError(error);
    } else if (requirementResponse.equals("Other")
        && (requirementResponse.getApplicationDomainOther() == null || requirementResponse
            .getApplicationDomainOther().trim().length() == 0)) {
      FieldError error = new FieldError(ATTR_REQUIREMENT_RESPONSE,
          "requirementResponse.applicationDomain", messageSource.getMessage("mandatory.if.answer",
              new String[] { "Other", "indicative name for the category in the textbox" },
              Locale.getDefault()));
      result.addError(error);
    }
    return returnValue;
  }
}
