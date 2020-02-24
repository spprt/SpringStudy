package com.springstudy.controller;

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
import com.springstudy.service.BoardService;
import com.springstudy.util.Criteria;
import com.springstudy.util.PageMaker;

@Controller
public class BoardController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView goWrite() throws Exception {
		ModelAndView mv = new ModelAndView("board/write");
		return mv;
	}

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView goList(Model model, Criteria cri) throws Exception {
		ModelAndView mv = new ModelAndView("board/list");

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCnt());

		mv.addObject("list", service.selectList(cri));
		mv.addObject("page", pageMaker);

		return mv;
	}

	@RequestMapping(value = "/board/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Board board, MultipartHttpServletRequest mpReq) throws Exception {
		service.write(board, mpReq);
		return "redirect:/board/view?id=" + board.getId();
	}

	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView read(Board board, Model model) throws Exception {
		logger.info("read :::: " + board.getId());
		model.addAttribute("read", service.read(board.getId()));
		return new ModelAndView("board/view");
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
