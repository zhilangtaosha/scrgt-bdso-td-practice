# Celebrity Recommendations


## User Stories
* The BioCeleb Entity Corporation has been hired to collect Biographical and Biometric information for GlobalTier Studios. The studio has agreed to hire the top 100 celebrities for a massive movie production and wants to create a celebrity profile and conduct entity resolution and analytics across the group. 

* They have collection biographic and biometric information on top 100 Celebrity from different open source databases and news feed.

* For a given celebrity, recommend alternative actors based on similar characteristics.


## Celebrity Recommendations

When building a recommendation engine, we identify which criteria and KPIs makes a good recommendation.  We apply those signals to measure how effective the suggested results sets are.  To do that, we need to understand what signals the end users look for when asking for a recommendation.

For the BioCeleb Entity Corp, they have a pool of the top 100 celebrities to match different criteria for their next movie. The would like to match celebrities with similar experiences working on different movie genres. They might consider looking the celebrities background to determine how best to pair other actors together.  

The casting team, they might want to identify celebrity with a certain look to play the leading role on a upcoming film.  They might compare biometric feature to match a certain charter and style.

For screenwriters, they might want identify celebrites that can play different type of acting style.  How succesfull have them been in those type of roles.


## Data Models

The data science team evaluated the data inputs from the theMovieDB, IMDB, and Wikipedia to source information about the celebrities, their roles, and the movies they performed in.  To build the recommendation models we applied different NLP/ML/AI models to demonstrate our solutios.

Bio Similarities :  Using the IMDB Celebrity Bio page, we ingested the celebrity biography and synopys.  The information contains a history of the movies and mentions about the celebrity.  We applied a NLP model to identify keywords and phrases that best describe the celebrity.  The keywords are then feed into ELMo: a Deep contextualized word representation to represent the semantics of the bio.
The bio vectors are then feed into the recommendation engine (Elasticsearch).  Using a cosine simimlarity measurement, we measured the distance between different celebrities. Only the top celebrities are returned.

Genre History Similarities: Using the filmography history of the celebrity, we identify the number of movies performed in the different movie genre.  The more time the celebrity performed in that genre we increase the score by 1.  We did not apply any weights to the numbers if the celebrity was the lead or supporting cast member.  We take the stats and normalized the scores across all the movie genres. We then compare the genre vectors to other celebrities.

Movie Plots: Expanding on the model above, we take the movie plots by genre. We applied the same NLP modeling in the Bio Similarites to extract the semantic of the movie plots that the celebrity have performed in the past.  The movie plots are represented in dense vector space and loaded to the recommendation engine. For a given celebrity by a genre, you can compare how similar other celebrites performed.






