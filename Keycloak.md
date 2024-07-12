

**Роли и термины**:  
`Ресуры` - некоторые данные пользователя
Владелец ресурсов - пользователь (имеет учетную запись пользователя)  
`Сервер ресурсов` - сервис, хранящий ресуры пользователя - доверяет ключам, выпускаемым сервером авторизации  
`Клиент` - приложение, требующее доступ к ресурсам пользователя (зарегистрирован как клиент на сервере)  
`Сервер авторизации` - посредник между клиетом, владельцем ресурсов и сервером ресурсов  

OIDC - open-id-connect 
`OpenID Connect` - это совместимый протокол аутентификации, основанный на спецификациях OAuth 2.0 framework

---

BasicAuth - самая проста реализация
Всегда Stateless - сессия вечная

---

**Клиенты**:  
- публичные - не может проходить аутентификацию клиента (веб-приложения - браузерные, а также нативные - мобильные и настольные), поскольку невозможно хранить секретную информацию надежно
- конфиденциальные - может проходить аутентификацию клиента, секретные данные хранятся в надежном месте (клиент-серверное приложение)

Ключи(токены):  
- Ключ доступа (Access token) - предоставляет доступ к ресурсу
- Ключ обновления (Refresh token) - позволяет получить новые ключи доступа - нужен, чтобы всякий раз не спрашивать разрешения у пользователя на аналогичные операции клиентом
- Код доступа - промежуточный код, позволяющий получить ключ доступа
- Идентификационный ключ (ID token) - ключ, идентифицирующий пользователя в OIDC(??? - крч показывает, что пользователь уже аутентифицирован)

Область применения (Scope)
Специфичные для клиента и сервера ресурсов ограничение способов применения ключа доступа.
Например: calendar, calendar:create, task:view и т.д.
Стандартные области применения в OIDC: openid, profile, email, address, phone

> Пользователь после авторизации - Principal principal в аргументах контроллера (вся инфа из JWT-токена)

Настройка для `клиента keycloak`  
```yml
spring:
  security:
    oauth2:
      client:
        provider:
          keycloak:
            authorization-uri: ${keycloak.auth-server-uri}/realms/${keycloak.realm}/protocol/openid-connect/auth
            issuer-uri: ${keycloak.auth-server-uri}/realms/${keycloak.realm}
            token-uri: ${keycloak.auth-server-uri}/realms/${keycloak.realm}/protocol/openid-connect/token
            user-info-uri: ${keycloak.auth-server-uri}/realms/${keycloak.realm}/protocol/openid-connect/userinfo
            jwk-set-uri: ${keycloak.auth-server-uri}/realms/${keycloak.realm}/protocol/openid-connect/certs
        registration:
          discovery:
            provider: keycloak #должно быть идентичным значению провайдера выше
            client-id: ${keycloak.resource}
            client-secret: ${keycloak.client-key-password}
            authorization-grant-type: client_credentials
#            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope:
              - discovery #для работы с эврикой
```

Client внутри keycloak:
```java
            // Получаем ресурс ClientsResource
            ClientsResource clientsResource = adminClient.realm(realmName).clients();

            // Создаем новый клиент
            ClientRepresentation clientRepresentation = new ClientRepresentation();
            clientRepresentation.setClientId(clientId);
            clientRepresentation.setPublicClient(false); // Логический флаг, определяющий, является ли клиент публичным.
            clientRepresentation.setEnabled(true); // Включаем клиента.
            clientRepresentation.setDirectAccessGrantsEnabled(true); // Разрешаем непосредственный доступ к API.

            // Добавляем роль к клиенту
            clientRepresentation.addRedirectUri("http://localhost:8080/callback"); // URL для обратного вызова.
            clientRepresentation.addWebOrigins("http://localhost:8080"); // Допустимые URL-адреса происхождения для запросов от приложения. 
            clientRepresentation.addProtocolMapper("oidc-usermodel-role-mapper");

            // Создаем клиента (или обновляем, если он уже существует)
            clientsResource.create(clientRepresentation);

            System.out.println("Клиент Keycloak " + clientId + " создан успешно!");
        }
    }
}
```

UserRepresentation
```json
{
  "unmanagedAttributePolicy": "DISABLED",
  "attributes": [
    {
      "name": "myattribute",
      "multivalued": false,
      "displayName": "My Attribute",
      "group": "personalInfo",
      "required": {
        "roles": [ "user", "admin" ],
        "scopes": [ "foo", "bar" ]
      },
      "permissions": {
        "view": [ "admin", "user" ],
        "edit": [ "admin", "user" ]
      },
      "validations": {
        "email": {
          "max-local-length": 64
        },
        "length": {
          "max": 255
        }
      },
      "annotations": {
        "myannotation": "myannotation-value"
      }
    }
  ],
  "groups": [
    {
      "name": "personalInfo",
      "displayHeader": "Personal Information",
      "annotations": {
        "foo": ["foo-value"],
        "bar": ["bar-value"]
      }
    }
  ]
}

my
{
"iat": 1716572072,
  "jti": "fa93bfaf-5625-4583-a3e5-439cce74c755",
  "iss": "http://localhost:8080/realms/gnivc",
  "aud": "account",
  "sub": "d364d00e-9f36-4b67-ba7d-9348c559cb7e",
  "typ": "Bearer",
  "azp": "gnivc-client",
  "session_state": "fd8b5ab3-4113-4b8b-a4eb-46840f563e8a",
  "acr": "1",
  "allowed-origins": [
    "/*"
  ],
  "realm_access": {
    "roles": [
      "offline_access",
      "uma_authorization",
      "default-roles-gnivc"
    ]
  },
  "resource_access": {
    "account": {
      "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
      ]
    }
  },
  "scope": "profile email",
  "sid": "fd8b5ab3-4113-4b8b-a4eb-46840f563e8a",
  "email_verified": false,
  "name": "Umpa Lumpa",
  "preferred_username": "admin",
  "given_name": "Umpa",
  "family_name": "Lumpa",
  "email": "timofeev.vadim.96@mail.ru"
}
```

Зависимости  

Сервер авторизации:  
```xml
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-oauth2-resource-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-oauth2-jose</artifactId>
        </dependency>
```

`Настройки клиента Keycloak`:
```java
@Configuration
@RequiredArgsConstructor
public class ConfigurationBeans {
    private final KeycloakProperties keycloakProperties;

    //через клиента
//    @Bean
//    public Keycloak keycloak() {
//        return KeycloakBuilder.builder()
//                .serverUrl(keycloakProperties.authServerUrl)
//                .realm(keycloakProperties.realm)
//                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                .clientId(keycloakProperties.resource)
//                .clientSecret(keycloakProperties.clientKeyPassword)
//                .build();
//    }

    //через пользователя
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.serverUrl)
                .realm(keycloakProperties.realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(keycloakProperties.resource)
                .clientSecret(keycloakProperties.clientKeyPassword)
                .username(keycloakProperties.adminUsername)
                .password(keycloakProperties.adminPassword)
                .build();
    }
}
```