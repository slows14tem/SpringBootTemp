타임리프 (데이터와 템플릿을 결합해주는 도구)

	JSP는 스프링에서 권장하지 않음 -> 템플릿엔진을 사용한 화면개발을 권고
	사용자에게 제공되는 화면과 데이터를 분리하여 관리 가능

	타임리프 플러그인 설치
	pom.xml : spring-boot-starter-thymeleaf 추가
	application.properties : spring.thymeleaf.cache=false
	
사용자 인증 (세션 사용하여 로그인 인증)

	@SessionAttributes("member") 어노테이션 사용
	뷰어에서 로드 : ${session['member'].name}