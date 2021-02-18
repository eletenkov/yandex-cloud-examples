package org.buraindo.todo;

import com.jsoniter.output.JsonStream;
import org.buraindo.todo.db.Dao;
import org.buraindo.todo.db.TaskDao;
import org.buraindo.todo.model.Task;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListTasksServlet extends HttpServlet {

    private final Dao<Task> taskDao = new TaskDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var tasks = taskDao.findAll();
        var tasksJsonString = JsonStream.serialize(tasks);

        var out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(tasksJsonString);
        out.flush();
    }

}
