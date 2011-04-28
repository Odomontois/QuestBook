package ru.nol.qbook;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import ru.nol.qbook.persistence.Chat;
import ru.nol.qbook.persistence.PMF;
import ru.nol.qbook.persistence.Some;

public class QuestBookServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7844601037722042965L;
	public final static String ACTION = "action";
	public final static String ID = "id";
	public final static String VALUE = "value";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello!!!!");
		final String action = req.getParameter(ACTION);
		if (action == null) {
			resp.getWriter().println("wut???");
			return;
		}
		switch (Actions.valueOf(req.getParameter(ACTION))) {
		case create:
			createSome(req.getParameter(ID), req.getParameter(VALUE));
			resp.getWriter().println("succesfully created");
			break;
		case read:
			resp.getWriter().println(
					"value is " + getSome(req.getParameter(ID)).getValue());
			break;
		default:
			resp.getWriter().println("unknown action");
		}
	}

	private Some getSome(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			return pm.getObjectById(Some.class, id);
		} finally {
			pm.close();
		}
	}

	private void createSome(String id, String value) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Some some = new Some(id);
			Chat chat  = new Chat(id);
			some.setValue(value);
			pm.makePersistent(some);
			pm.makePersistent(chat);
		} finally {
			pm.close();
		}

	}

	enum Actions {
		create, read
	}
}
