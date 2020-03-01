package com.springstudy.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.entity.Board;
import com.springstudy.entity.Employee;
import com.springstudy.service.BoardService;
import com.springstudy.service.EmployeeService;
import com.springstudy.util.AuthInfo;
import com.springstudy.util.PageMaker;
import com.springstudy.util.SearchCriteria;

@Controller
public class BoardController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private BoardService service;

	@Autowired
	private EmployeeService empSer;

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView write() throws Exception {
		ModelAndView mv = new ModelAndView("board/write");
		return mv;
	}

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView list(Model model, SearchCriteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("board/list");

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.selectCount(cri));

		mv.addObject("list", service.selectList(cri));
		mv.addObject("page", pageMaker);

		return mv;
	}

	@RequestMapping(value = "/board/articles", method = RequestMethod.GET)
	public ModelAndView articles(Long empid, Model model, SearchCriteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("board/articles");

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCnt(empid));

		mv.addObject("list", service.selectList(cri, empid));
		mv.addObject("page", pageMaker);

		try {
			Employee emp = empSer.getEmployee(empid);
			if (null != emp)
				mv.addObject("writerName", emp.getName());
		} catch (Exception e) {
			logger.info("BoardController : not Matched employee" + empid);
		}

		return mv;
	}

	@RequestMapping(value = "/board/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Board board, MultipartHttpServletRequest mpReq, HttpSession session)
			throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (null != authInfo) {
			board.setWriterId(authInfo.getId());
		}
		service.write(board, mpReq);
		return "redirect:/board/view?id=" + board.getId();
	}

	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView read(Board board, Model model) throws Exception {
		logger.info("read :::: " + board.getId());
		model.addAttribute("read", service.read(board.getId()));
		return new ModelAndView("board/view");
	}

	@RequestMapping(value = "/board/fileDown", method = RequestMethod.GET)
	public void fileDown(Long id, HttpServletResponse response) throws Exception {
		service.fileDown(id, response);
	}

	@RequestMapping(value = "/board/edit", method = RequestMethod.GET)
	public ModelAndView edit(Board board, Model model) throws Exception {
		logger.info("edit :::: " + board.getId());
		model.addAttribute("read", service.read(board.getId()));
		return new ModelAndView("board/edit");
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Board board) throws Exception {
		logger.info("update :::: " + board.getId());
		service.update(board);
		return "redirect:/board/view?id=" + board.getId();
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(Board board) throws Exception {
		logger.info("delete :::: " + board.getId());
		service.delete(board.getId());
		return "redirect:/board/list";
	}
}
