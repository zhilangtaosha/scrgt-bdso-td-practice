export const environment = {
  production: false,
  ENV_NAME: 'local',
  USE_MOCK: false,
  API_URL: 'http://localhost:8080/api/v1',
  RECOMMENDATION_API: 'https://scrgt-bdso-recommendation-dev.ocp.testing.salientcrgt-bdso.com',
  ELASTIC_URL: 'https://elasticsearchdb-dev.ocp.testing.salientcrgt-bdso.com',
  AUTHENTICATION_URL: '',
  LOGOUT_URL: '',
  KEYCLOAK_CONFIG: {
    realm: 'bdso',
    url: 'https://keycloak-dev.ocp.testing.salientcrgt-bdso.com/auth',
    clientId: 'celebrity-ui'
  }
};
