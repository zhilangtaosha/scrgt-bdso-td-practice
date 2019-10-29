import { KeycloakService } from 'keycloak-angular';

import { environment } from '../../environments/environment';

export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      const { KEYCLOAK_CONFIG } = environment;
      try {
        await keycloak.init({
          config: KEYCLOAK_CONFIG,
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false
          },
          enableBearerInterceptor: true,
          bearerExcludedUrls: ['/assets', "https://api.nytimes.com/svc/search/v2/articlesearch.json"]
        });
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  };
}
