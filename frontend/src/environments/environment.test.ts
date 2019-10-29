export const environment = {
  production: false,
  ENV_NAME: 'test',
  USE_MOCK: false,
  API_URL: 'http://scrgt-bdso-celebrity-test.ocp.DOMAIN/api/v1',
  RECOMMENDATION_API: 'https://scrgt-bdso-recommendation-test.ocp.DOMAIN',
  ELASTIC_URL: 'https://elasticsearchdb-test.ocp.DOMAIN',
  AUTHENTICATION_URL: '',
  LOGOUT_URL: '',
  KEYCLOAK_CONFIG: {
    realm: 'Bdso',
    url: 'https://keycloak-dev.ocp.DOMAIN',
    clientId: 'celebrity-ui',
  }
};
