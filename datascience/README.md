## Data Sources

  

*  <strong> Top100Actors</strong> : Provided by the client

  

* This will be the reference table for the actor

  

*  <strong>IMDB</strong> : IMDb is the world's most popular and authoritative source for movie, TV and celebrity content. Find ratings and reviews for the newest movie and TV shows.

  

https://www.imdb.com

  

*  <strong>The Movie Database (TMDb) </strong> is a popular, user editable database for movies and TV shows.

  

https://www.themoviedb.org/

  

*  <strong>Wikipedia </strong> is a free online encyclopedia, created and edited by volunteers around the world and hosted by the Wikimedia Foundation.

  

https://www.wikipedia.org/

*  <strong>Additonal Data Sources </strong>

* Facebook

* Twitter

* Instagram

## Data Model

  

![Conceptual Model](images/conceptual_model.png?raw=true)



## Actor Dimension:

  

The refenece table for the list of unique actors to extract data from different open source databases/sites

  

| Column | Description |

|------|------------|

| _id | unique actor ID |

| First Name | first name |

| Last Name | last name |

| D_Box_Office | Domestic Box Office |

| Movies | No of movies |

| Avg_Rev | Average Revenue per movies |

| IMDB | external IMDB ID |

| theMovieDB | external themoviedb ID |

  

| _id | First Name | Last Name | D_Box_Office | Movies | Average_Rev | IMDB | theMovieDB |

|------|------------|-------------|---------------|----|--------------|-----| -----|

| 1 | Robert | Downey, Jr. | 5,374,149,735 | 43 | 124,980,226 | 123 | 3453 |

| 2 | Samuel L. | Jackson | 5,234,777,328 | 60 | 87,246,289 | 232 | 2222 |

| 3 | Scarlett | Johansson | 4,859,403,632 | 28 | 173,550,130 | 344 | 4324 |

  
  
  
  

### Actor Documents

  

Each data source has it's own schema with different overlapping data element. We will preseve the original documents and append the following attributes to the json.

  

```

{'_actorID': <internal actor ID> ,

'_source': <source name>,

'actorID': <external ID>,

'created_utc_dt' : <utc timestamp>

}

```

### Document folder

- <<"S3 data folder">>

    - actor

        - imdb

            - imdb_a_[actorID].json

        - themobiedb

        - wikipedia

## Movie Dimension:

  

The movie reference table containing information about the following data elements.

  

| Column| Description |

|------|------------|

| _id | unique movie iD |

| title | move title |

| budget | budget |

| genres | list of genres |

| homepage | movie homepage |

| original_language | orignal language |

| original_title | orignal title |

| overview | movie synoyms |

| popularity | popularity score |

| poster_img | poster image |

| production_companies | production companies|

| release_date | release date |

| revenue | revenue |

| runtime | length of the movie |

| spoken_languages | original language |

| status | movie status |

| tagline | tageline |

| imdb_id | external imdb ID |

| themoviedb_id | external themoviedb ID |

  
  

Each data source has it's own schema with different overlapping data element. We will preseve the original documents and append the following attributes to the json.

  

```

{'_movieID': <internal movie ID> ,

'_source': <source name>,

'movieID': <external ID>,

'created_utc_dt' : <utc timestamp>

}

```

  

### Document folder

- <<"S3 data folder">>

    - movie

        - imdb

            - imdb_m_[movieID].json

        - themobiedb

        - wikipedia

## Cast Dimension:

  

The cast reference table contain the relationship between the actor and the movie based on the character they played.

  

| Column| Description|

|------|------------|

| _castID | unique cast ID |

| _actorID | reference of the actor playing the role |

| _movieID | refence of the movie the actor played in |

| character | character name |

| desc | charter description |

  
  

### Document folder

- <<"S3 data folder">>

    - casts

        - themoviedb_c_[actorID].json