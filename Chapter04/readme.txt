1. JPA 프로젝트 (Without Spring)
	JPA 구현체로 하이버네이트 사용
	JPA는 /META-INF/persistence.xml파일을 가장 먼저 로딩한 후, 영속성 유닛 정보를 이용
	persistence.xml -> hibernate.dialect 속성을 통해 사용하는 데이터베이스에 최적화된 sql생성가능
	persistence.xml -> hibernate.hdm2ddl.auto=create => 테이블과 시퀀스를 자동으로 생성 
	
	
2. 영속성 컨텐스트란 엔티티 객체를 관리하는 일종의 컨테이너이고 EntityManager를 통해서 접근 가능
	영속성 컨텍스트에 등록된 엔티티는 EntityManager가 제공하는 메소드를 통해 관리
	
	Entity의 상태
		비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 상태(객체만 생성된 단계)
		영속(managed): 영속성 컨텍스트에 저장된 상태
		준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
		삭제(removed): 삭제된 상태
	
	1차 캐시
		persist로 엔티티가 영속화되면 엔티티는 영속성컨텍스가 가진 1차캐시에 등록(일종의 map)
		-> transaction commit 으로 트랜잭션이 종료될 때 실제 데이터베이스에 반영(Flush 과정)
	
	SQL 저장소
		persist로 영속성 컨텍스트에 저장하면 엔티티를 1차캐시에 등록한 후, 1차 캐시에 등록된 엔티티에 해당하는 insert구문을 생성하여 sql저장소에 등록
		-> transaction commit 으로 트랜잭션이 종료될 때 누적되어있던 sql들이 한꺼번에 실제 데이터베이스에 전송(성능 최적화 가능)
	
	SnapShot
		hibernate.hdm2ddl.auto=update
		find()로 검색된 엔티티가 영속성 컨텍스트에 저장될 때 엔티티의 복사본을 만들어서 별도의 컬렉션(스냅샷)에 저장
		트랜젝션 종료시 스냅샷에 저장된 원래의 엔티티와 1차캐시의 수정된 엔티티를 비교해서 변경된 값을 이용해서 update 수행
		기본적으로 하나의 필드만 수정해도 모든 필드를 수정함 (쿼리문이 수정될 필요가 없어서 성능상으로 이득)
