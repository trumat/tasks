package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    public static final String CREATED_TRELLO_CARD = "New Trello card email";
    public static final String DAILY_MAIL = "Scheduled daily mail";

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildEmail(String message, final String templateType) {
        String email;
        if (templateType == CREATED_TRELLO_CARD) {
            email = buildTrelloCardEmail(message);
        } else if (templateType == DAILY_MAIL) {
            email = buildDailyMail(message);
        } else {
            throw new MailSendException("No such template");
        }
        return email;
    }

    private String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending cards to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8000");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Kind regards,");
        context.setVariable("signature", companyConfig.getOwnerName() + " " + companyConfig.getOwnerSurname());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_address", companyConfig.getStreetNumber() + " " + companyConfig.getStreetName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    private String buildDailyMail(String message) {
        List<Task> tasks = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks", tasks);
        context.setVariable("tasks_url", "http://localhost:8000");
        context.setVariable("button", "Manage tasks");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_config", companyConfig);
        context.setVariable("noTasks", taskRepository.count() == 0);
        context.setVariable("goodbye_message", "Regards,");

        return templateEngine.process("mail/daily-mail", context);
    }
}
