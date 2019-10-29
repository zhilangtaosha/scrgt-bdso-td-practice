
class   Filmography {
  ranking: number;
  numberOfMovies: number;
  listOfMoviesByYear: MovieCount[];
  movies: Movies[];
}

class Cast {
  celebrityId: number;
  id: number;
  movieId: number;
  roleName: string;
}

export class Movies {
  title: string;
  genres: string[];
  releaseDate: string;
  boxOffice: string;
  cast: Cast[];
  salary: string;
}

class MovieSource {
  source: string;
  logo: string;
  data: string;
}

class FRResultsData {
  celebrity: string;
  _actorID: string;
  score: string;
}

class SearchResult {
  name: string;
  gender: string;
  ethnicity: string;
  age: string;
  averageSalary: string;
  genres: string[];
  experienceLevel: string;
  image: string;
}

export enum ROLES {
  USER = 'user',
  SUPERVISOR = 'supervisor',
  DATA = 'datascientist'
}

export class Celebrity {
  id: number;
  birthName: string;
  firstName: string;
  lastName: string;
  image: string;
}

export class FacialRecognitionData {
  status: string;
  celebrity: string;
  results: FRResultsData[];
}

export class CelebrityDetails {
  id: number;
  imdbId: string;
  movdbId: string;
  birthName: string;
  firstName: string;
  lastName: string;
  nickName: string;
  dob: string;
  birthPlace: string;
  gender: string;
  height: string;
  age: number;
  genres: string[];
  experienceLevel: string[];
  image: string;
  imageUrl: string;
  popularity: number;
  totalBoxOffice: string;
  averageBoxOffice: string;
  biography: string;
  sources: MovieSource[];
  filmography: Filmography;
}

export class CelebrityChangelog {
  type: CelebrityChangeType;
  changeDate: Date;
  fieldName: string;
  changedBy: string;
  changes: Changes;
}

export class Changes {
  oldValue: string;
  newValue: string;
}

export enum CelebrityChangeType {
  Biographical
}

export class SearchFieldParams {
  names: string;
  experienceLevel: string[];
  ageRange: string[];
  genre: string[];
  gender: string;
}

export class SearchResults {
  hasData: boolean;
  data: CelebrityDetails[];
}

export class MovieCount {
  year: string;
  movieCount: number;
}

export class CelebritySalary {
  year: string;
  salary: AnnualSalary[];
}

export class AnnualSalary {
  year: string;
  salary: number;
}

export class FilmsByGender {
  gender: string;
  movieCount: number;
  year: number;
}

export class SalaryByGender {
  gender: string;
  salary: number;
  year: number;
}

export class AnnualTotals {
  boxOffice: number;
  movieCount: number;
  year: number;
}

export class SummaryCounts {
  movieCount: number;
  totalBoxOffice: number;
  multipleActorCount: number;
}
