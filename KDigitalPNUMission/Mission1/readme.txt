1. list 데이터를 생성해서 요청하는 정보를 제공하는 버전

2. 기본 구조: Controller - Service

3. Service에서 list 데이터를 생성 및 제공한다.

@ 어노테이션
	@SpringBootApplication : 스프링부트로 만든 어플리케이션의 시작 클래스임을 의미
		@ComponentScan: 사용자가 정의한 클래스가 속해있는 패키지를 베이스 패키지로 하여 빈 등록을 처리
						(@Configureation, @Repository, @Service, @Controller 등)
	@RestController : REST방식의 응답을 처리하는 컨트롤러 구현. VO(Value Object)객체를 리턴하는 경우
					VO객체를 JSON데이터로 변환하여 응답프로토콜 바디에 출력
					@Controller은 리턴타입으로 문자열을 사용하여 그 문자열에 해당하는 View를 만들어야함
	