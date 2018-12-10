package io.github.cepr0.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@SpringBootApplication
public class Application {

	private final ChildRepo childRepo;
	private final ParenRepo parenRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener
	public void onReady(ApplicationReadyEvent event) {

		Parent p1 = parenRepo.save(new Parent("parent1", "123456"));

		childRepo.saveAll(asList(
			new Child("child1", p1),
			new Child("child2", p1)
		));

		Integer parentId = p1.getId();

//		parenRepo.getParentWithChildNamesAsJson(parentId)
//				.ifPresent(prj -> System.out.printf("%s, %s", prj.getName(), prj.getChildNames()));

		parenRepo.getParentWithChildNamesAsArray(parentId)
				.ifPresent(prj -> System.out.printf("%s, %s", prj.getName(), prj.getChildNames()));
	}
}
