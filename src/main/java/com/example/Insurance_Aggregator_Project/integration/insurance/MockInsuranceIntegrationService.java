package com.example.Insurance_Aggregator_Project.integration.insurance;


import com.example.Insurance_Aggregator_Project.Advise.ApiException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@ConditionalOnProperty(name="integration.insurance.isMock",havingValue = "true",matchIfMissing = true)
@Service
@Transactional
public class MockInsuranceIntegrationService implements InsuranceIntegrationService {


    @Value("${google.sheets.credentials.file.path}")
    private String credentialsPath;

    @Value("${google.sheets.spreadsheet.id}")
    private String spreadsheetId;

    @Value("${google.sheets.sheet.name}")
    private String sheetName;

    @Value("${google.sheets.range}")
    private String range;


    private final ObjectMapper objectMapper;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);


    private Sheets getInsuranceSheets() throws IOException, GeneralSecurityException {

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(ResourceUtils.getFile(credentialsPath)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();


        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        final Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName("Insurance Aggregator")
                .build();

    }

    @Override
    public List<InsuranceQuote> getInsuranceQuotes() {
        try {
            Sheets sheetsService = getInsuranceSheets();
            String range = sheetName + "!" + this.range;
            final var values = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute()
                    .getValues();


            return objectMapper.readValue(values.get(0).toString() , new TypeReference<List<InsuranceQuote>>(){});
        } catch (Exception e) {
            throw new ApiException(e);
        }

    }
}
