package com.task.tracker.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questions.beans.AddCommentBean;
import com.interview.questions.beans.QuestionBean;
import com.interview.questions.beans.TestBean;
import com.interview.questions.beans.TestValidationBean;
import com.interview.questions.beans.UpdateQuestionBean;
import com.interview.questions.db.dao.QuestionDao;
import com.interview.questions.db.dao.TestModelDao;
import com.interview.questions.db.entities.CommentModel;
import com.interview.questions.db.entities.QuestionModel;
import com.interview.questions.db.entities.TestModel;
import com.interview.questions.db.entities.TestQuestionModel;
import com.interview.questions.db.entities.UserModel;
import com.interview.questions.populator.EntryImpl;
import com.interview.questions.populator.Populator;
import com.task.tracker.services.TagService;
import com.task.tracker.services.TestService;
import com.task.tracker.services.UserServiceCustom;


@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	private Populator<TestBean, TestModel > populator;
	
	@Autowired
	private Populator<TestModel, TestBean> beanPopulator;
	
	@Autowired
	private Populator<TestQuestionModel, QuestionBean> questionBeanPopulator;
	
	@Autowired
	private Populator <QuestionModel, TestQuestionModel> testQuestionPopulator;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	TestModelDao testModelDao;
	
	@Autowired
	UserServiceCustom userServiceCustom;
	
	@Override
	public List<QuestionBean> getBeans(TestBean testBean, HttpSession session)
	{
		TestModel createTest = createTest(testBean);
		session.setAttribute("test",createTest);
		List<QuestionBean> beanslist = new ArrayList<QuestionBean>();
		
 		for(TestQuestionModel sa : createTest.getQuestions())
 		{
 			QuestionBean bean = new QuestionBean();
 			bean.setNumber(sa.getNumber());
 			bean.setQuestionTags(sa.getTags());
 			bean.setQuestionText(sa.getQuestionText());
 			beanslist.add(bean);
 		}
 		return beanslist;
	}

	@Override
	public TestModel createTest(TestBean testBean) {
		
		TestModel testModel = populator.populate(testBean);
		
		
		Collection<QuestionModel> generateQuestions = generateQuestions(testBean, testBean.getNumberOfQuestions());
		int i = 1;
		for(QuestionModel question : generateQuestions)
		{
			TestQuestionModel questionTest = testQuestionPopulator.populate(question);
			questionTest.setNumber(i++);
			
//			TestQuestionModel testQuestion = new TestQuestionModel();
			
//			testQuestion.setNumber(i++);
//			testQuestion.setQuestionText(question.getQuestionText());
//			testQuestion.setQuestionType(question.getQuestionType());
//			testQuestion. setTags(tagService.getTagsForIds(question.getTags()));
//			if()
//			for()
			
			
			testModel.getQuestions().add(questionTest);
		}
		testModelDao.save(testModel);
		String aa = testModel.get_id().toString();
		String x = testModel.get_id().toHexString();
		aa.equals(x);
		TestModel tm = testModelDao.findTestById(new ObjectId(aa));
		tm.equals(testModel);
		return testModel;
		
	}
	
	private Collection<QuestionModel> generateQuestions(TestBean testBean ,int number)
	{
		List<QuestionModel> questions = questionDao.getQuestionNumberByTag( testBean.getQuestionTags() );
		Set<QuestionModel> set = new HashSet<QuestionModel>();
		
		Random random = new Random(); 
		
		if(number >= questions.size())
		{
			return questions;
		}
		
		else 
		{
			while(set.size()< number)
			{
				set.add(questions.get(random.nextInt(questions.size())));
			}
			
		}
		
		return set;
		
		
	}

	@Override
	public List<TestQuestionModel> generateQuestions(TestModel testBean) {
		
//		testBean.ge
//		questionDao
		return null;
	}

	@Override
	public TestModel findTestById(String _id) {
		
		TestModel findTestById = testModelDao.findTestById(new ObjectId(_id));
		// TODO Auto-generated method stub
		return findTestById;
	}
	
	@Override
	public void updateQuestion(UpdateQuestionBean updateQuestionBean, HttpSession session)
	{
		TestModel testModel =   (TestModel) session.getAttribute("test"); 
		
		QuestionModel newQuestion = questionDao.findQuestionById(updateQuestionBean.getId());
		TestQuestionModel newTestQuestion = null;
		
		for(TestQuestionModel currentQuestion : testModel.getQuestions())
		{
			if(currentQuestion.getNumber() == updateQuestionBean.getNumber())
			{
				currentQuestion = testQuestionPopulator.populate(newQuestion);
				currentQuestion.setNumber(updateQuestionBean.getNumber());
				newTestQuestion = currentQuestion;
				
//				question.setQuestionText(newQuestion.getQuestionText());
//				question.setTags(tagService.getTagsForIds(newQuestion.getTags()));
//				question.setQuestionType(newQuestion.getQuestionType());
				break;
			}
		}
		testModel.getQuestions().set(updateQuestionBean.getNumber() -1, newTestQuestion);
		testModelDao.save(testModel);
		
	}

	@Override
	public TestModel findTestForCandidate(TestValidationBean bean) {
		
		TestModel testModel = testModelDao.findTestById(new ObjectId(bean.getId()));
		if(null != testModel && testModel.getCandidateMail().equalsIgnoreCase(bean.getMail()))
		{
			return testModel;
					
		}
		return null;
	}
	
	@Override
	public void save(TestModel testModel)
	{
		testModelDao.save(testModel);
	}

	@Override
	public TestBean populateBean(TestModel test) {
		
		return beanPopulator.populate(test);
	}

	@Override
	public QuestionBean getQuestionBean(int questionId, HttpSession session) {
		
		TestModel test = (TestModel) session.getAttribute("reviewTest");
		List<TestQuestionModel> questions = test.getQuestions();
		QuestionBean questionBean = null;
		
		for(TestQuestionModel testQuestion : questions)
		{
			if(testQuestion.getNumber() == questionId)
			{
				questionBean = questionBeanPopulator.populate(testQuestion);
				break;
			}
		}
		return questionBean;
	}

	@Override
	public void addComment(TestModel testModel, AddCommentBean bean, String mail) {
		
		for (TestQuestionModel question : testModel.getQuestions())
		{
			if(question.getNumber() == bean.getNumberId())
			{
				List<CommentModel> commentsList = question.getComments();
				if(null == commentsList)
				{
					commentsList = new ArrayList<>();
				}

				CommentModel comment = new CommentModel();
				comment.setCommentText(bean.getComment());
				comment.setDate(new Date());
				comment.setAuthor(userServiceCustom.getNameByEmail(mail));
				commentsList.add(comment);
				question.setComments(commentsList);
				break;
			}
		}
		
		testModelDao.save(testModel);
		
	}

	@Override
	public List <EntryImpl<String, String>> getTestForReview(String mail) {
		
		UserModel currentUser = userServiceCustom.getUserByMail(mail);
		List<TestModel> testsForReviewList = testModelDao.findTestByUserAndStatus(currentUser.getId(), true);
		List <EntryImpl<String, String>> entriesList = new ArrayList<>();
		for(TestModel test : testsForReviewList)
		{
			EntryImpl <String,String>entry = new EntryImpl <>(test.get_id().toString(), test.getCandidateName());
			entriesList.add(entry);
		}
			return entriesList;
		
	}


}
