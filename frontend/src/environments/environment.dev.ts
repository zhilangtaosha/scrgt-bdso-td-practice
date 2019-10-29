export const environment = {
  production: false,
  ENV_NAME: 'dev',
  USE_MOCK: false,
  API_URL: 'https://scrgt-bdso-cc-ms-celebrity-dev.ocp.DOMAIN/api/v1',
  RECOMMENDATION_API: 'https://scrgt-bdso-recommendation-dev.ocp.DOMAIN',
  ELASTIC_URL: 'https://elasticsearchdb-dev.ocp.DOMAIN',
  AUTHENTICATION_URL: '',
  LOGOUT_URL: '',
  KEYCLOAK_CONFIG: {
    realm: 'bdso',
    url: 'https://keycloak-dev.ocp.DOMAIN/auth',
    clientId: 'celebrity-ui',
  }
};
