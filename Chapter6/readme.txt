뷰어 (JSP파일로 사용)
	pom.xml - JSTL, tomcat-embed-jasper 의존성 추가
	application.properties - JSP ViewResolver Setting 필요
		spring.mvc.view.prefix=/WEB-INF/앱이름/
		spring.mvc.view.suffix=.jsp
		
	src/main/webapp/WEB-INF/앱이름 폴더의 JSP파일을 뷰로 사용
	
	컨트롤러의 리턴값이 뷰 이름