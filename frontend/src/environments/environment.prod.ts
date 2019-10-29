export const environment = {
  production: true,
  ENV_NAME: 'prod',
  USE_MOCK: false,
  API_URL: 'http://scrgt-bdso-celebrity-prod.ocp.DOMAIN/api/v1',
  RECOMMENDATION_API: 'https://scrgt-bdso-recommendation-prod.ocp.DOMAIN',
  ELASTIC_URL: 'https://elasticsearchdb-prod.ocp.DOMAIN',
  AUTHENTICATION_URL: '',
  LOGOUT_URL: '',
  KEYCLOAK_CONFIG: {
    realm: 'Bdso',
    url: 'https://keycloak-dev.ocp.DOMAIN',
    clientId: 'celebrity-ui',
  }
};
