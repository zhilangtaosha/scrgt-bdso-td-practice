// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const { SpecReporter } = require('jasmine-spec-reporter');
const { JUnitXmlReporter } = require('jasmine-reporters');

exports.config = {
  allScriptsTimeout: 11000,
  specs: [
    './src/**/*.e2e-spec.ts'
  ],
  capabilities: {
    'browserName': 'chrome',
    binary: process.env.CHROME_BIN,
    chromeOptions: {
      args: ["--headless", "--disable-gpu",'--no-sandbox']
    }
  },
  chromeDriver: '/usr/bin/chromedriver',
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function() {}
  },
  onPrepare() {
    require('ts-node').register({
      project: require('path').join(__dirname, './tsconfig.json')
    });

    // const specReporter = new SpecReporter({
    //   spec: { displayStacktrace: true }
    // });
    jasmine.getEnv().addReporter(new SpecReporter({
            displayStacktrace: 'all',      // display stacktrace for each failed assertion, values: (all|specs|summary|none)
            displaySuccessesSummary: false, // display summary of all successes after execution
            displayFailuresSummary: true,   // display summary of all failures after execution
            displayPendingSummary: true,    // display summary of all pending specs after execution
            displaySuccessfulSpec: true,    // display each successful spec
            displayFailedSpec: true,        // display each failed spec
            displayPendingSpec: false,      // display each pending spec
            displaySpecDuration: false,     // display each spec duration
            displaySuiteNumber: false,      // display each suite number (hierarchical)
            colors: {
                success: 'green',
                failure: 'red',
                pending: 'yellow'
            },
            prefixes: {
                success: '✓ ',
                failure: '✗ ',
                pending: '* '
            },
            savePath: './e2e/test-results/E2E',
            customProcessors: []
        }));
//    jasmine.getEnv().addReporter(specReporter);

    const junitReporter = new JUnitXmlReporter({
      savePath: './e2e/test-results/E2E',
      consolidateAll: false
    });
    jasmine.getEnv().addReporter(junitReporter);
  }
};
