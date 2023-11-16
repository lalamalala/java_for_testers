package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import io.swagger.client.auth.*;
import io.swagger.client.api.UserApi;


public class RestApiHelper extends HelperBase {


    public RestApiHelper(ApplicationManager manager) {
    super(manager);
    ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
}

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId= new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categorytId= new Identifier();
        categorytId.setId(issueData.category());
        issue.setCategory(categorytId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);

        }
    }

    public void createUser(UserData userdata) {

        UserApi apiInstance = new UserApi();
        User body = new User();
        body.username(userdata.username());
        body.password(userdata.password());
        body.email(userdata.email());
        try {
         //   UserAddResponse result = apiInstance.userAdd(body);
         //   System.out.println(result);
            apiInstance.userAdd(body);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userDelete");
            e.printStackTrace();
        }

    }
}
