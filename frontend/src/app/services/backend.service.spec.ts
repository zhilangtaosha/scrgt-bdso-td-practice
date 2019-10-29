import { TestBed, inject } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { BackendService } from "./backend.service";
import { Celebrity, CelebrityDetails } from "../models/models";
import { environment } from "src/environments/environment";
import { celebritiesData } from "../mock/api/backend";

describe("BackendService", () => {
  let backendservice: BackendService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BackendService]
    });
    backendservice = TestBed.get(BackendService);
    httpMock = TestBed.get(HttpTestingController);
  });
  it("should be created", () => {
    expect(backendservice).toBeTruthy();
  });
  afterEach(() => {
    httpMock.verify();
  });

  it("should be created", () => {
    const service: BackendService = TestBed.get(BackendService);
    const httpMock: HttpTestingController = TestBed.get(HttpTestingController);
    expect(service).toBeTruthy();
  });

  it("should retrive all celebrities data from the Api via GET", () => {
    let postData: Celebrity[] = [
      {
        id: 1,
        birthName: "John",
        firstName: "Can",
        lastName: "sdf",
        image: "sdfsd"
      },
      {
        id: 2,
        birthName: "New",
        firstName: "YEs",
        lastName: "sdf",
        image: "sdfsd"
      }
    ];
    backendservice.getAllCelebritiesData().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(postData);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.celebrities}`
    );
    expect(request.request.method).toBe("GET");

    request.flush(postData);
  });
  it("should Set a celebrity's data via POST", () => {
    let UpdateData: CelebrityDetails = {
      id: 2,
      imdbId: "Ins",
      imageUrl: "ssad",
      movdbId: "232",
      birthName: "sdfsd",
      firstName: "asd",
      lastName: "asdas",
      nickName: "sd",
      dob: "asdas",
      birthPlace: "sadas",
      gender: "sdsa",
      height: "sdsa",
      age: 2314,
      genres: ["asd", "sad"],
      experienceLevel: ["asd", "sad"],
      image: "asdasds",
      popularity: 213,
      totalBoxOffice: "sdas",
      averageBoxOffice: "sadsa",
      biography: "sadas",
      sources: [],
      filmography: {
        ranking: 22,
        numberOfMovies: 21,
        listOfMoviesByYear: [],
        movies: []
      }
    };
    backendservice.setCelebrityData(UpdateData).subscribe(posts => {
      expect(posts).toEqual(UpdateData);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.celebrities}`
    );
    expect(request.request.method).toBe("POST");

    request.flush(UpdateData);
  });
  it("should get celebrity's data by ID Via GET", () => {
    let celebId = 4;
    backendservice.getCelebrityData(celebId).subscribe(posts => {
      expect(posts).toEqual(celebId);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.celebrities}/${celebId}`
    );
    expect(request.request.method).toBe("GET");

    request.flush(celebId);
  });
  it("should get celebrity's data by Actor ID Via GET", () => {
    let actorsIds = "actorID";
    let actorsData: Celebrity[] = [
      {
        id: 1,
        birthName: "John",
        firstName: "Can",
        lastName: "sdf",
        image: "sdfsd"
      },
      {
        id: 2,
        birthName: "New",
        firstName: "YEs",
        lastName: "sdf",
        image: "sdfsd"
      }
    ];
    backendservice.getCelebritiesByActorsIds(actorsIds).subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(actorsData);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.celebrityByActorId}/?${actorsIds}`
    );
    expect(request.request.method).toBe("GET");

    request.flush(actorsData);
  });

  it("should Get search result Via GET", () => {
    let searchParams: any[];
    backendservice.getSearchResult(searchParams).subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(searchParams);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.search}?query=${searchParams}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get Film totals Via GET", () => {
    let filmTotal: {
      movieCount: 3;
      totalBoxOffice: 34;
      multipleActorCount: 32;
    };
    backendservice.getFilmsTotal().subscribe(posts => {
      expect(posts.movieCount).toBe(3);
      expect(posts.multipleActorCount).toEqual(32);
      expect(posts.totalBoxOffice).toEqual(34);
      expect(posts).toEqual(filmTotal);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.summaryCounts}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get films by year results Via GET", () => {
    let movieCounts: [
      {
        year: "2019";
        movieCount: 200;
      },
      {
        year: "2018";
        movieCount: 180;
      }
    ];
    backendservice.getFilmsData().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(movieCounts);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.filmsData}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get actors salaries by year results Via GET", () => {
    let celebritySalary: [
      {
        year: "2018";
        salary: 2133;
      },
      {
        year: "2018";
        salary: 1800;
      },
      {
        year: "2019";
        salary: 200;
      },
      {
        year: "2018";
        salary: 180;
      }
    ];
    backendservice.getCelebritySalaryData().subscribe(posts => {
      expect(posts.length).toBe(4);
      expect(posts).toEqual(celebritySalary);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.salaryByYear}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get num of films by gender Via GET", () => {
    let filmsByGenderData: [
      {
        gender: "male";
        movieCount: 20;
        year: 2018;
      },
      {
        gender: "female";
        movieCount: 800;
        year: 201;
      }
    ];
    backendservice.getFilmsByGenderData().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(filmsByGenderData);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.filmsByGenderData}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get salary by gender Via GET", () => {
    let salaryByGenderData: [
      {
        gender: "Male";
        salary: 24234;
        year: 2019;
      },
      {
        gender: "Female";
        salary: 20234;
        year: 2018;
      }
    ];
    backendservice.getSalaryByGenderData().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts[0].gender).toEqual("Male");
      expect(posts).toEqual(salaryByGenderData);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.salaryByGenderData}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get box office and film total by year Via GET", () => {
    let filmTotalByYear: [
      {
        boxOffice: 23123;
        movieCount: 343432;
        year: 2018;
      },
      {
        boxOffice: 23123;
        movieCount: 343432;
        year: 2018;
      }
    ];
    backendservice.getAnnualTotals().subscribe(posts => {
      expect(posts.length).toBe(2);
      expect(posts).toEqual(filmTotalByYear);
    });
    const request = httpMock.expectOne(
      `${environment.API_URL}${backendservice.apiEndpoints.annualTotals}`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get Recommendation by Similar Bios Via GET", () => {
    let celebName: "James Will";
    let getRecommendation: {
      status: "new";
      celebrity: "John Tom";
      results: [];
    };

    backendservice.getSimilarBiosRecommendation(celebName).subscribe(posts => {
      expect(posts).toEqual(getRecommendation);
    });
    const request = httpMock.expectOne(
      `${environment.RECOMMENDATION_API}${backendservice.apiRecommendationEndpoints.similarBiosRecommendation}/${celebName}/`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get Recommendation by Similar Genres Plots Via GET", () => {
    let celebName: "James Will";
    let getRecommendation: {
      status: "new";
      celebrity: "John Tom";
      results: [];
    };

    backendservice
      .getSimilarGenreStatsRecommendation(celebName)
      .subscribe(posts => {
        expect(posts).toEqual(getRecommendation);
      });
    const request = httpMock.expectOne(
      `${environment.RECOMMENDATION_API}${backendservice.apiRecommendationEndpoints.similarGenreStatsRecommendation}/${celebName}/`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get Recommendation by Similar Genres Plots Via GET", () => {
    let celebName: "James Will";
    let getRecommendation: {
      status: "new";
      celebrity: "John Tom";
      results: [];
    };

    backendservice
      .getSimilarGenrePlotsRecommendation(celebName)
      .subscribe(posts => {
        expect(posts).toEqual(getRecommendation);
      });
    const request = httpMock.expectOne(
      `${environment.RECOMMENDATION_API}${backendservice.apiRecommendationEndpoints.similarGenrePlotsRecommendation}/${celebName}/`
    );
    expect(request.request.method).toBe("GET");
  });
  it("should get Recommendation by Face Via GET", () => {
    let celebName: "James Will";
    let getRecommendation: {
      status: "new";
      celebrity: "John Tom";
      results: [];
    };

    backendservice.getFacialRecommendation(celebName).subscribe(posts => {
      expect(posts).toEqual(getRecommendation);
    });
    const request = httpMock.expectOne(
      `${environment.RECOMMENDATION_API}${backendservice.apiRecommendationEndpoints.facialRecommendation}/${celebName}/`
    );
    expect(request.request.method).toBe("GET");
  });
});
