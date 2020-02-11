package org.vote.api.vote;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.vote.beans.Apply;
import org.vote.common.BaseApi;
import org.vote.common.UUIDTool;
import org.vote.common.Utils;

/**
 * 处理活动报名
 */
@WebServlet("/api/vote/apply")
public class ApplySingle extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ApplySingle() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");

    apply.setAid(aid);
    Apply apply = new Apply();

    } catch (NumberFormatException e) {
    

  boolean isMyActivity = isMyActivity(request, response);
  if (isMyActivity) apply.setStatus('Y');

        // 执行数据存储
        if (saveInstance(apply)) {
          sendJSON(response, apply);
        } else {
          complete(response, 1401);
        }
      } catch (Exception e) {
        e.printStackTrace();
        complete(response, 1401);
      }
    }
  }
}