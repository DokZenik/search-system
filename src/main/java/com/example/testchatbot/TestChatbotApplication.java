package com.example.testchatbot;

import com.example.testchatbot.domen.Degree;
import com.example.testchatbot.domen.Department;
import com.example.testchatbot.service.DepartmentLectureService;
import com.example.testchatbot.service.DepartmentService;
import com.example.testchatbot.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class TestChatbotApplication implements CommandLineRunner {

    private final DepartmentService departmentService;
    private final DepartmentLectureService departmentLectureService;
    private final LecturerService lecturerService;

    public static void main(String[] args) {
        SpringApplication.run(TestChatbotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String answer = "";
        String[] templates = new String[]{
                "Who is head of department ...",
                "Show ... statistics",
                "Show the average salary for development ...",
                "Show count of employee for ...",
                "Global search by ...",
                "Exit"
        };
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\nChoose one option:\n");
        for (String template: templates) {
            System.out.println(template);
        }
        System.out.println("\n\nImportant!\nInstead of \"...\" you must put your value");
        String processedString;
        while (true){
            System.out.println("\n\nEnter your query from list: ");
            answer = scanner.nextLine();

            if(answer.startsWith(templates[0].substring(0, templates[0].length() - 4))){

                processedString = answer.substring(templates[0].replaceFirst(" ...", "").length() + 1);

                try {
                    System.out.println("Head of " + processedString + " department is " + departmentService.getHeadByName(processedString));
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }

            } else if (answer.startsWith("Show") && answer.endsWith("statistics")) {
                processedString = answer.substring("Show".length() + 1, answer.length() - "statistics".length() - 1);
                Map<Degree, Long> departmentLongMap = departmentLectureService.countEmployees(processedString);
                System.out.println("assistants - " + departmentLongMap.get(Degree.ASSISTANT)
                        + "\nassociate professors - " + departmentLongMap.get(Degree.ASSOCIATE_PROFESSOR)
                        + "\nprofessors - " + departmentLongMap.get(Degree.PROFESSOR));

            }else if(answer.startsWith(templates[2].substring(0, templates[2].length() - 4))){
                processedString = answer.substring(templates[2].replaceFirst(" ...", "").length() + 1);
                try {
                    System.out.println("The average salary of " + processedString + " is " + departmentLectureService.averageSalaryByDepartmentName(processedString));
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            } else if (answer.startsWith(templates[3].substring(0, templates[3].length() - 4))) {
                processedString = answer.substring(templates[3].replaceFirst(" ...", "").length() + 1);

                try {
                    System.out.println(departmentLectureService.employeeNumberByDepartment(processedString));
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            } else if (answer.startsWith(templates[4].substring(0, templates[4].length() - 4))) {
                processedString = answer.substring(templates[4].replaceFirst(" ...", "").length() + 1);

                System.out.println("Departments: ");
                departmentService.globalSearch(processedString).forEach(System.out::println);
                System.out.println("Lectures: ");
                lecturerService.globalSearch(processedString).forEach(System.out::println);

            } else if(answer.equalsIgnoreCase("exit"))
                break;

            else {
                System.out.println("!!!Incorrect input!!!");
            }
        }
    }
}
