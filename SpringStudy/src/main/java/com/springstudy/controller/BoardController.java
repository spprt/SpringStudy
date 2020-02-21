package com.springstudy.controller;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.entity.Board;
import com.springstudy.service.BoardService;

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
	public ModelAndView goList(String page) throws Exception {
		ModelAndView mv = new ModelAndView("board/list");
		System.out.println("requestPage ::: " + page);
		int curPage = 0;
		if (page == null) {
		} else {
			curPage = Integer.valueOf(page);
		}
		
		mv.addObject("list", service.selectList(curPage));
		mv.addObject("totalCount", service.totalCnt());
		mv.addObject("page", curPage);

		return mv;
	}

	@RequestMapping(value = "/board/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Board board) throws Exception {
		service.write(board);
		return new ModelAndView("board/list");
	}

	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView read(Board board, Model model) throws Exception {
		logger.info(board.getId());
		System.out.println("read :::: " + board.getId());
		model.addAttribute("read", service.read(board.getId()));
		return new ModelAndView("board/view");
	}

	@RequestMapping(value = "/board/edit", method = RequestMethod.GET)
	public ModelAndView edit(Board board, Model model) throws Exception {
		logger.info(board.getId());
		System.out.println("edit :::: " + board.getId());
		model.addAttribute("read", service.read(board.getId()));
		return new ModelAndView("board/edit");
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute Board board) throws Exception {
		logger.info(board.getId());
		System.out.println("update :::: " + board.getId());
		service.update(board);
		return new ModelAndView("/board/list");
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView delete(Board board) throws Exception {
		logger.info(board.getId());
		System.out.println("delete :::: " + board.getId());
		service.delete(board.getId());
		return new ModelAndView("/board/list");
	}
}
