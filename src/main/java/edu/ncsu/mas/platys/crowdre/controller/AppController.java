package edu.ncsu.mas.platys.crowdre.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import edu.ncsu.mas.platys.crowdre.form.PostsurveyResponseForm;
import edu.ncsu.mas.platys.crowdre.form.PresurveyResponseForm;
import edu.ncsu.mas.platys.crowdre.form.PersonalityResponseForm;
import edu.ncsu.mas.platys.crowdre.form.CreativityResponseForm;
import edu.ncsu.mas.platys.crowdre.form.RequirementRatingResponseForm;
import edu.ncsu.mas.platys.crowdre.model.PostsurveyQuestion;
import edu.ncsu.mas.platys.crowdre.model.PostsurveyResponse;
import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;
import edu.ncsu.mas.platys.crowdre.model.PersonalityQuestion;
import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;
import edu.ncsu.mas.platys.crowdre.model.RequirementRatingResponse;
import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;
import edu.ncsu.mas.platys.crowdre.model.User;
import edu.ncsu.mas.platys.crowdre.model.PresurveyResponse;
import edu.ncsu.mas.platys.crowdre.model.PersonalityResponse;
import edu.ncsu.mas.platys.crowdre.model.CreativityResponse;
import edu.ncsu.mas.platys.crowdre.service.PostsurveyQuestionService;
import edu.ncsu.mas.platys.crowdre.service.PostsurveyResponseService;
import edu.ncsu.mas.platys.crowdre.service.PresurveyQuestionService;
import edu.ncsu.mas.platys.crowdre.service.PersonalityQuestionService;
import edu.ncsu.mas.platys.crowdre.service.CreativityQuestionService;
import edu.ncsu.mas.platys.crowdre.service.PresurveyResponseService;
import edu.ncsu.mas.platys.crowdre.service.PersonalityResponseService;
import edu.ncsu.mas.platys.crowdre.service.CreativityResponseService;
import edu.ncsu.mas.platys.crowdre.service.RequirementRatingResponseService;
import edu.ncsu.mas.platys.crowdre.service.RequirementResponseService;
import edu.ncsu.mas.platys.crowdre.service.RequirementSelectorService;
import edu.ncsu.mas.platys.crowdre.service.UserService;
import edu.ncsu.mas.platys.crowdre.util.RandomCodeGenerator;

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

  @Autowired
  RequirementRatingResponseService requirementRatingResponseService;

  @Autowired
  PostsurveyQuestionService postsurveyQuestionService;

  @Autowired
  PostsurveyResponseService postsurveyResponseService;

  @Autowired
  RequirementSelectorService requirementSelectorService;

  private static final String PAGE_SIGNIN = "signin";
  private static final String PAGE_SIGNIN_FAILURE = "signin_failure";
  private static final String PAGE_REDIRECT_SIGNIN_FAILURE = "redirect:signin_failure";

  private static final String PAGE_PRESURVEY = "presurvey";
  private static final String PAGE_REDIRECT_PRESURVEY = "redirect:presurvey";

  private static final String PAGE_PERSONALITY = "personality";
  private static final String PAGE_REDIRECT_PERSONALITY = "redirect:personality";

  private static final String PAGE_CREATIVITY = "creativity";
  private static final String PAGE_REDIRECT_CREATIVITY = "redirect:creativity";

  private static final String PAGE_REQUIREMENTS_PHASE1 = "requirements_phase1";
  private static final String PAGE_REDIRECT_REQUIREMENTS_PHASE1 = "redirect:requirements_phase1";

  private static final String PAGE_REQUIREMENTS_PHASE2 = "requirements_phase2";
  // private static final String PAGE_REDIRECT_REQUIREMENTS_PHASE2 =
  // "redirect:requirements_phase2";

  private static final String PAGE_POSTSURVEY = "postsurvey";
  private static final String PAGE_REDIRECT_POSTSURVEY = "redirect:postsurvey";

  private static final String PAGE_SUCCESS = "success";
  private static final String PAGE_REDIRECT_SUCCESS = "redirect:success";

  private static final String PAGE_ERROR = "error";

  private static final String USER_ENTITY = "userEntity";
  private static final String PERSONALITY_SCORES_ENTITY = "personalityScoresEntity";
  private static final String OTHER_REQUIREMENT_RESPONSES_ENTITY = "otherRequirementResponsesEntity";

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
  private static final String ATTR_DOMAIN_COUNTS = "domainCounts";
  private static final String ATTR_OTHERS_REQUIREMENT_RESPONSES = "othersRequirementResponses";

  private static final String ATTR_REQUIREMENT_RATING_RESPONSE_FORM = "requirementRatingResponseForm";

  private static final String ATTR_POSTSURVEY_QUESTIONS = "postsurveyQuestions";
  private static final String ATTR_POSTSURVEY_RESPONSE_FORM = "postsurveyResponseForm";

  private static final int MTURK_ID_VALID = 0;
  private static final int MTURK_ID_INVALID = 1;
  private static final int MTURK_ID_COMPLETED = 2;

  private final RandomCodeGenerator randCodeGen = new RandomCodeGenerator(8);

  @RequestMapping(value = { "/", "/" + PAGE_SIGNIN }, method = RequestMethod.GET)
  public String showSignIn(ModelMap model) {
    model.addAttribute(ATTR_USER, new User());
    return PAGE_SIGNIN;
  }

  @RequestMapping(value = { "/", "/" + PAGE_SIGNIN }, method = RequestMethod.POST)
  public String processSignIn(@ModelAttribute(ATTR_USER) User user,
      final RedirectAttributes redirectAttributes, HttpSession session) {

    user.setCreatedAt(LocalDateTime.now());
    user.setCreatedPhase(Integer.parseInt(env.getProperty("study.phase")));
    session.setAttribute(USER_ENTITY, user);

    switch (isMturkIDValid(user.getMturkId())) {
    case MTURK_ID_VALID:
      userService.saveResponse(user);
      return PAGE_REDIRECT_PRESURVEY;

    case MTURK_ID_COMPLETED:
      redirectAttributes
          .addFlashAttribute(
              ATTR_SIGN_FAILURE_REASON,
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
  public String showSigninFailure(
      @ModelAttribute(ATTR_SIGN_FAILURE_REASON) String signinFailureReason, BindingResult result,
      ModelMap model) {
    model.addAttribute(ATTR_SIGN_FAILURE_REASON, signinFailureReason);
    return PAGE_SIGNIN_FAILURE;
  }

  @RequestMapping(value = { "/" + PAGE_PRESURVEY }, method = RequestMethod.GET)
  public String showPresurvey(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

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

    if (isPresurveyResponseFormValid(presurveyResponseForm, result, model)) {
      PresurveyResponse[] presurveyResponses = presurveyResponseForm.getPresurveyResponses();
      for (int i = 0; i < presurveyResponses.length; i++) {
        presurveyResponses[i].setCreatedAt(LocalDateTime.now());
        presurveyResponseService.saveResponse(presurveyResponses[i]);
      }
      return PAGE_REDIRECT_PERSONALITY;
    } else { // Page has errors
      // This should never happen since this form is validated client side
      return PAGE_ERROR;
    }
  }

  @RequestMapping(value = { "/" + PAGE_PERSONALITY }, method = RequestMethod.GET)
  public String showPersonality(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

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
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes,
      HttpSession session) {

    Double[] personalityScores = new Double[20];
    if (isPersonalityResponseFormValid(personalityResponseForm, result, model)) {
      PersonalityResponse[] personalityResponses = personalityResponseForm
          .getPersonalityResponses();
      for (int i = 0; i < personalityResponses.length; i++) {
        personalityResponses[i].setCreatedAt(LocalDateTime.now());
        personalityResponseService.saveResponse(personalityResponses[i]);

        personalityScores[personalityResponses[i].getPersonalityQuestionId() - 1] = Double
            .parseDouble(personalityResponses[i].getDescription());
      }
      session.setAttribute(PERSONALITY_SCORES_ENTITY, personalityScores);

      return PAGE_REDIRECT_CREATIVITY;
    } else { // Page has errors
      // This should never happen since this form is validated client side
      return PAGE_ERROR;
    }
  }

  @RequestMapping(value = { "/" + PAGE_CREATIVITY }, method = RequestMethod.GET)
  public String showCreativity(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

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

    if (isCreativityResponseFormValid(creativityResponseForm, result, model)) {
      CreativityResponse[] creativityResponses = creativityResponseForm.getCreativityResponses();
      for (int i = 0; i < creativityResponses.length; i++) {
        creativityResponses[i].setCreatedAt(LocalDateTime.now());
        creativityResponseService.saveResponse(creativityResponses[i]);
      }
      return PAGE_REDIRECT_REQUIREMENTS_PHASE1;
    } else { // Page has errors
      // This should never happen since this form is validated client side
      return PAGE_ERROR;
    }
  }

  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE1 }, method = RequestMethod.GET)
  public String showRequirementsPhase1(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

    List<RequirementResponse> previousRequirementResponses = requirementResponseService
        .findByUserId(user.getId());
    model.addAttribute(ATTR_PREVIOUS_REQUIREMENT_RESPONSES, previousRequirementResponses);
    model.addAttribute(ATTR_DOMAIN_COUNTS, countDomains(previousRequirementResponses));

    @SuppressWarnings("unchecked")
    List<RequirementResponse> othersRequirementResponses = (List<RequirementResponse>) session
        .getAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY);
    
    if (othersRequirementResponses == null) {
      Double[] personalityRawScores = (Double[]) session.getAttribute(PERSONALITY_SCORES_ENTITY);
      othersRequirementResponses = requirementSelectorService.getClosestRequirementsFromRawScores(
          personalityRawScores, true, 10); // TODO Generalize this
      session.removeAttribute(PERSONALITY_SCORES_ENTITY);
      session.setAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY, othersRequirementResponses);
    }
    
    model.addAttribute(ATTR_OTHERS_REQUIREMENT_RESPONSES, othersRequirementResponses);

    RequirementResponse requirementResponse = new RequirementResponse();
    requirementResponse.setUserId(user.getId());
    model.addAttribute(ATTR_REQUIREMENT_RESPONSE, requirementResponse);

    return PAGE_REQUIREMENTS_PHASE1;
  }

  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE1 }, method = RequestMethod.POST)
  public String processRequirementsPhase1Response(
      @ModelAttribute(ATTR_REQUIREMENT_RESPONSE) RequirementResponse requirementResponse,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes,
      HttpSession session) {
    if (isRequirementResponseValid(requirementResponse, result, model)) {
      requirementResponse.setCreatedAt(LocalDateTime.now());
      requirementResponse.setShowOther(1);
      requirementResponseService.saveResponse(requirementResponse);
      return PAGE_REDIRECT_REQUIREMENTS_PHASE1;
    } else { // Page has errors
      User user = (User) session.getAttribute(USER_ENTITY);
      List<RequirementResponse> previousRequirementResponses = requirementResponseService
          .findByUserId(user.getId());
      model.addAttribute(ATTR_PREVIOUS_REQUIREMENT_RESPONSES, previousRequirementResponses);
      model.addAttribute(ATTR_DOMAIN_COUNTS, countDomains(previousRequirementResponses));

      @SuppressWarnings("unchecked")
      List<RequirementResponse> othersRequirementResponses = (List<RequirementResponse>) session
          .getAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY);

      if (othersRequirementResponses == null) {
        Double[] personalityRawScores = (Double[]) session.getAttribute(PERSONALITY_SCORES_ENTITY);
        othersRequirementResponses = requirementSelectorService
            .getClosestRequirementsFromRawScores(personalityRawScores, true, 10);
        session.removeAttribute(PERSONALITY_SCORES_ENTITY);
        session.setAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY, othersRequirementResponses);
      }

      model.addAttribute(ATTR_OTHERS_REQUIREMENT_RESPONSES, othersRequirementResponses);

      model.addAttribute(ATTR_REQUIREMENT_RESPONSE, requirementResponse);
      return PAGE_REQUIREMENTS_PHASE1;
    }
  }

  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE2 }, method = RequestMethod.GET)
  public String showRequirementsPhase2(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

    @SuppressWarnings("unchecked")
    List<RequirementResponse> othersRequirementResponses = (List<RequirementResponse>) session
        .getAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY);
    session.removeAttribute(OTHER_REQUIREMENT_RESPONSES_ENTITY);

    List<RequirementResponse> previousRequirementResponses = requirementResponseService
        .findByUserId(user.getId());

    previousRequirementResponses.addAll(othersRequirementResponses);
    Collections.shuffle(previousRequirementResponses);

    RequirementRatingResponse[] ratingResponses = new RequirementRatingResponse[previousRequirementResponses
        .size()];
    for (int i = 0; i < ratingResponses.length; i++) {
      ratingResponses[i] = new RequirementRatingResponse();
      ratingResponses[i].setRequirementResponse(previousRequirementResponses.get(i));
    }

    RequirementRatingResponseForm responseForm = new RequirementRatingResponseForm();
    responseForm.setRequirementRatingResponses(ratingResponses);
    model.addAttribute(ATTR_REQUIREMENT_RATING_RESPONSE_FORM, responseForm);

    return PAGE_REQUIREMENTS_PHASE2;
  }

  @RequestMapping(value = { "/" + PAGE_REQUIREMENTS_PHASE2 }, method = RequestMethod.POST)
  public String processRequirementsPhase2Response(
      @ModelAttribute(ATTR_REQUIREMENT_RATING_RESPONSE_FORM) RequirementRatingResponseForm responseForm,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes,
      HttpServletRequest request, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);
    
    if (isRequirementRatingResponseFormValid(responseForm, result, model)) {
      RequirementRatingResponse[] requirementRatingResponses = responseForm
          .getRequirementRatingResponses();
      for (int i = 0; i < requirementRatingResponses.length; i++) {
        RequirementResponse requirementResponse = new RequirementResponse();
        requirementResponse.setId(Integer.parseInt(request
            .getParameter("requirementRatingResponses[" + i + "].requirementResponse.id")));
        requirementRatingResponses[i].setCreatedAt(LocalDateTime.now());
        requirementRatingResponses[i].setUserId(user.getId());
        requirementRatingResponseService.saveResponse(requirementRatingResponses[i]);
      }

      user.setCompletionCode(randCodeGen.nextString());
      userService.updateResponse(user);

      return PAGE_REDIRECT_POSTSURVEY;
    } else { // Page has errors
      // This should never happen since this form is validated client side
      return PAGE_ERROR;
    }
  }

  @RequestMapping(value = { "/" + PAGE_POSTSURVEY }, method = RequestMethod.GET)
  public String showPostsurvey(ModelMap model, HttpSession session) {

    User user = (User) session.getAttribute(USER_ENTITY);

    int numQuestions = (int) postsurveyQuestionService.getCount();
    PostsurveyQuestion[] postsurveyQuestions = new PostsurveyQuestion[numQuestions];
    PostsurveyResponse[] postsurveyResponses = new PostsurveyResponse[numQuestions];

    for (int i = 0; i < numQuestions; i++) {
      postsurveyQuestions[i] = postsurveyQuestionService.findById(i + 1);
      postsurveyResponses[i] = new PostsurveyResponse();
      postsurveyResponses[i].setUserId(user.getId());
      postsurveyResponses[i].setPostsurveyQuestionId(postsurveyQuestions[i].getId());
    }

    PostsurveyResponseForm postsurveyResponseForm = new PostsurveyResponseForm();
    postsurveyResponseForm.setPostsurveyResponses(postsurveyResponses);

    model.addAttribute(ATTR_POSTSURVEY_QUESTIONS, postsurveyQuestions);
    model.addAttribute(ATTR_POSTSURVEY_RESPONSE_FORM, postsurveyResponseForm);

    return PAGE_POSTSURVEY;
  }

  @RequestMapping(value = { "/" + PAGE_POSTSURVEY }, method = RequestMethod.POST)
  public String processPostsurveyResponse(
      @ModelAttribute(ATTR_POSTSURVEY_RESPONSE_FORM) PostsurveyResponseForm postsurveyResponseForm,
      BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {

    if (isPostsurveyResponseFormValid(postsurveyResponseForm, result, model)) {
      PostsurveyResponse[] postsurveyResponses = postsurveyResponseForm.getPostsurveyResponses();
      for (int i = 0; i < postsurveyResponses.length; i++) {
        postsurveyResponses[i].setCreatedAt(LocalDateTime.now());
        postsurveyResponseService.saveResponse(postsurveyResponses[i]);
      }
      return PAGE_REDIRECT_SUCCESS;
    } else { // Page has errors
      // This should never happen since this form is validated client side
      return PAGE_ERROR;
    }
  }

  @RequestMapping(value = { "/" + PAGE_SUCCESS }, method = RequestMethod.GET)
  public String showSuccess(ModelMap model) {
    return PAGE_SUCCESS;
  }

  private Map<String, Integer> countDomains(List<RequirementResponse> previousRequirementResponses) {
    Map<String, Integer> domainCounts = new LinkedHashMap<String, Integer>();
    domainCounts.put("Health", 0);
    domainCounts.put("Safety", 0);
    domainCounts.put("Energy", 0);
    domainCounts.put("Entertainment", 0);
    domainCounts.put("Other", 0);

    for (RequirementResponse response : previousRequirementResponses) {
      String domain = response.getApplicationDomain();
      Integer count = domainCounts.get(domain);
      if (count == null) {
        count = 1;
      } else {
        count++;
      }
      domainCounts.put(domain, count);
    }
    return domainCounts;
  }

  private int isMturkIDValid(String mturkID) {
    if (mturkID == null || mturkID.trim().length() <= 3) {
      // Could not find much information on the Mturk ID specification.
      // The length 3 has been chosen intuitively.
      return MTURK_ID_INVALID;
    } else if (mturkID.equals("pmuruka") || mturkID.equals("najmeri")) {
      // Exceptions testing
      return MTURK_ID_VALID;
    } else if (userService.getResponseCount(mturkID) > 0) {
      return MTURK_ID_COMPLETED;
    }
    return MTURK_ID_VALID;
  }

  private boolean isPresurveyResponseFormValid(PresurveyResponseForm presurveyResponseForm,
      BindingResult result, ModelMap model) {
    // All validations are being done on the client side
    return true;
  }

  private boolean isPersonalityResponseFormValid(PersonalityResponseForm personalityResponseForm,
      BindingResult result, ModelMap model) {
    // All validations are being done on the client side
    return true;
  }

  private boolean isCreativityResponseFormValid(CreativityResponseForm creativityResponseForm,
      BindingResult result, ModelMap model) {
    // All validations are being done on the client side
    return true;
  }

  private boolean isRequirementResponseValid(RequirementResponse requirementResponse,
      BindingResult result, ModelMap model) {

    boolean returnValue = true;
    if (requirementResponse.getApplicationDomain().equals("Other")
        && (requirementResponse.getApplicationDomainOther() == null || requirementResponse
            .getApplicationDomainOther().trim().length() == 0)) {
      returnValue = false;
      FieldError error = new FieldError(ATTR_REQUIREMENT_RESPONSE, "applicationDomain",
          messageSource.getMessage("mandatory.if.answer", new String[] { "Other",
              "indicate a domain name in the textbox to the right" }, Locale.getDefault()));
      result.addError(error);
    }
    // Other fields are validated on the client side
    return returnValue;
  }

  private boolean isRequirementRatingResponseFormValid(
      RequirementRatingResponseForm requirementRatingResponseForm, BindingResult result,
      ModelMap model) {
    // All validations are being done on the client side
    return true;
  }

  private boolean isPostsurveyResponseFormValid(PostsurveyResponseForm presurveyResponseForm,
      BindingResult result, ModelMap model) {
    // All validations are being done on the client side
    return true;
  }
}
