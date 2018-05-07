<%@ page language="java" pageEncoding="UTF-8"%>   
<%@page  
    import="java.util.*,java.io.*,   
    java.text.SimpleDateFormat,org.apache.commons.fileupload.*, org.apache.commons.fileupload.disk.*,
    org.apache.commons.fileupload.servlet.* ,
    net.sf.json.JSONObject,com.urbanfit.apiserver.cfg.pop.SystemConfig"%>
<%@ page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@ page import="org.springframework.web.context.request.ServletWebRequest" %>

<%
			String base_image = request.getSession().getAttribute("base_image") + "";
			PrintWriter outPrint = response.getWriter();
			String basePath = SystemConfig.getString("client_upload_base");
        	String dirPath = SystemConfig.getString("kindeditor_upload_url");
        	//文件保存目录路径
			String savePath = basePath + dirPath;

        	String imageRoot = SystemConfig.getString("img_file_root");
        	String vistePath = SystemConfig.getString("kindeditor_visite_url");

			// 设置访问路径
        	String saveUrl = base_image + imageRoot + vistePath;

			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
			
			//最大文件大小
			long maxSize = 3000000000l;
			
			response.setContentType("text/html; charset=UTF-8");
			
			if(!ServletFileUpload.isMultipartContent(request)){
				outPrint.print(getError("请选择文件。"));
				return;
			}
			//检查目录
			File uploadDir = new File(savePath);
			
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			if(!uploadDir.isDirectory()){
				outPrint.print(getError("上传目录不存在。"));
				return;
			}
			//检查目录写权限
			if(!uploadDir.canWrite()){
				outPrint.print(getError("上传目录没有写权限。"));
				return;
			}
			
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if(!extMap.containsKey(dirName)){
				outPrint.print(getError("目录名不正确。"));
				return;
			}
			//创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						outPrint.print(getError("上传文件大小超过限制。"));
						return;
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						outPrint.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
						return;
					}
			
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					try{
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					}catch(Exception e){
						System.out.print(getError("上传文件失败。"));
						return;
					}
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", saveUrl + newFileName);
					outPrint.print(obj.toString());
				}
			}
		%>
<%!
			private String getError(String message) {
				JSONObject obj = new JSONObject();
				obj.put("error", 1);
				obj.put("message", message);
				return obj.toString();
			}
%>

