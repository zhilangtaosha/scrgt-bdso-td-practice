# Models.md

## _Introduction_

This document presents Team SCRGT’s approach to providing effective NLP/AI/ML solutions to the user stories in the coding challenge. Our solution leverages the latest advanced open source technology (all AI/ML models are open source) that are robust enough to meet different use cases to handle large volumes of data collected from multiple sources. This AI/ML solution demonstrates our methodology and approach toward using different algorithms to process both structured and unstructured data. We demonstrate our agile methodology to continually test/validate/deploy new AI/ML models into a CI/CD workflow. The process flow diagram below depicts the team’s agile AI/ML solution and how our team works with each step of the methodology.
![Agile](<../img/6818_07_01_Agile%20AI-ML%20Solution%20Methodology.png>)


## _Understanding of Challenge Statement & Use Case – user stories highlights_
To better tailor our solution, we defined how GlobalTier Studios might use the search, visualization, and analysis features by extrapolating use case ideas from the core user stories.
1. The GlobalTier Studios will hire the top 100 celebrities that match different criteria for their next movie. GlobalTier wants to match celebrities with similar experiences working on different movie genres by examining celebrities’ backgrounds to determine how best to pair other actors together.
2. The casting agency wants to identify celebrities with certain physical characteristics (biometric data) to play the leading and supporting roles in the upcoming film and wants to compare their biometric features to match a certain character style.
3. The screenwriters are producing a new screenplay and want to identify celebrities that performed in similar storylines or movie genres.  They want to compare their screenplay against the roles the celebrities have performed in the past.

When building a recommendation engine, we identified which criteria and Key Performance Indicators (KPIs) make a good recommendation.  We apply those signals to measure how effective the recommended results sets are to the end user.


## _Logic and Innovation in the Solution_
### **Solution Overview**
Celebrity Recommendation is based on similarity from three data sets:
1.  Structured data from actor filmographies including movie plots
2.  Text data from actor biographies
3.  Facial features from actor images

Recommendation can be made on any of the above aspects individually or any combination. In each case cosine similarity (explained later in this document) is used to compute the similarity between vectors. When multiple data sources are used, a weighted average or custom weighted score can be applied on the individual similarity scores is used to produce a composite score.  We will summarize how each model is generated

### **Strengths of Our Solution**
1. Used a robust set of structured and unstructured data to train our models. Having a rich and robust data set improves training models, resulting in better recommendations
2. Leveraged sophisticated and open source AI/ML techniques such as Embeddings from Language Models (ELMo). ELMo  outperformed and set new state-of-the-art baselines when compared to other embeddings such as Word2Vec or Doc2Vec.
3. Streamlined integration into automated CI/CD pipeline

### **Data Sets Used for Labeling and Training Models**
To build the celebrity recommendation engine, we sourced data from theMovieDB, IMDB, and Wikipedia. Each source contains similar information but also contain different data elements for a given celebrity, their roles and the movies they performed in. By expanding across multiple data sources, we built a more comprehensive data set, allowing us to fill in missing data elements from another source. Having a rich and robust data set improves the training the models, resulting in better recommendations. The following table presents data sources, elements/features extracted from such sources, and elements that are unique to a source.
![Data Sources](<../img/6818_11_01_Data%20Sources%20and%20Data%20Elements.png>)

### **Data Management**
In order to create a single celebrity view, all sourced documents are stored into the Elasticsearch (ES) database.  ES is a highly scalable open source full text search engine that runs on the Lucene - StandardAnalyzer for indexing and automatic type guessing.  ES provides different options to customize the search parameters from exact match to fuzzy logic.  Parameters can be tuned based on the search fields and apply different weights.  When searching for a document based on keywords, ES returns a set of documents that matches closest to the search criteria.

Each source document is tagged to the master celebrity portfolio that consolidates the common data elements into one view, while still preserving the original document state and lineage.  As new sources and documents are added, ES scales accordingly through its distributed cluster technology.

**Fuzzy Search**

Our fuzzy name search service was implemented by built-in fuzzy match functionality in ES. Records from the multiple data sources map to a single master record, which enables the system to pull all corresponding records into a single celebrity view. The fuzzy match returns an array of best matches ranked by similarity, which can be used as possible matches. We selected the best match to map the given name to the name and identifier in the master celebrity record.

**Open Source AI/ML Models Utilization**

We built the celebrity recommendation system on the concept of entity embedding to map high dimensional categorical variables into low-dimensional categorical variables while still representing similar entities closer together in the embedding space. Using a neural network to learn entity embeddings, we compacted the data into an embedded vector, where it was computed to determine the distance between two entities. The three primary purposes of entity embedding are displayed in the figure below.
![Embedding](<../img/6818_15_01_Purposes%20of%20Entity%20Embedding.png>)
Our AI/ML solutions demonstrate the different embedding techniques described below (word embedding, facial recognition, cosine similarity) to encode unstructured data into dense vector space to compute the similarities between celebrities across different dimensional categories.

1. Word Embedding

The celebrity data sources used in the solution come with rich textual data of actor biographies, casting roles, and the plots of movies they performed in. We leveraged the information contained in these texts using ELMo. ELMo is both a Natural Language Processing (NLP) and a Deep Learning/Artificial Intelligence technique that leverages deep Learning topologies and TensorFlow. This world-class pre-trained network is open-source, allowing us to leverage the achievements of a rapidly developing NLP/Deep Learning community.

ELMo uses information from the entire sentence that a word is embedded in to build a vector of weights to mathematically represent the word within the complete sentence environment surrounding it. For example: “_Jan read the book yesterday”_ and _“Please read that letter now.”_
![ELMo](<../img/6818_12_01_ELMo%20Architecture.png>)

The two occurrences of the verb “read” are the same in textual form (although differing in spoken form) but occur in different sentence environments and context. ELMo uses the information in the sentence to build a word’s weight vector based on the entire sentence that the word occurs in. Therefore, ELMo will produce a different weight vector for the two occurrences of read in the two sample sentences above. These weight vectors represent the two different meanings of the word read (past vs present tense).

2. Facial Recognition

The celebrity dataset contains profile images of the actors over time.  Using facial recognition algorithms, we apply the embedding technique to measure the facial landmarks between celebrities, as shown in the following figure, using Johnny Depp as an example.
![Facial](<../img/6818_13_01_Facial%20Recognition%20Measurements.png>)

3. Cosine Similarity

To compute celebrity similarity, we take a celebrity and find the dot product between its embedding vector and those of all the other celebrity. The dot product is the cosine distance between vectors that ranges from -1, most dissimilar, to +1, most similar.
![Cosine](<../img/6818_10_01_Cosine%20Similarity.png>)

Since each model produces an embedding vector space, we leverage ES to not only store the documents, but also to deploy our models into production.  Using ES, we store the embedding vectors into a dense vector datatype.  With ES script_score parameter, we applied the cosine similarity function into the search query.
![CosineCode](<../img/6818_09_01_Cosine%20Similarity%20Code.png>)

### **Approach on Training, Integrating, Validating, and Maintaining our Models**
The figure below details how the models used for celebrity recommendations were trained, integrated, and validated.
![Train Integrate Validate](<../img/6818_16_01_Train-Integrate-Validate%20Models.png>)

The following figure displays the results of the models. To further validate the models, we compared the results against each other to determine the correlation across different dimensions.
![Validate Results](<../img/6818_14_01_Model%20Validation%20Sample%20Results.png>)

### **How Models Are Maintained and Integrated into Automated CI/CD Pipeline**
The models were developed in Juptyer Python Notebooks, including themoviedbAPI.ipynb, themoviedbMovieAPI.ipynb, imdbPersonBio.ipynb, and wikipedia.ipynb. All notebooks and the intermediate data they produce and consume are submitted through our CI/CD pipeline and stored in a GitHub repository. Data Scientists can access, execute, and evaluate these notebooks. The celebrity recommendation was implemented as a service and exposed to the UI using the following steps.
* Developed each ML/AI model in Jupyter Notebooks
* Train/test new models in the Dev environment promoted up to different environments (Test, Prod), once it passes through all the approval and verification stages
* The output of each model is stored as a pickle file into an S3 bucket
* ES imports new models from the S3 into the appropriate indexes.  We provided a set of recommendation APIs for the application and admin to maintain the system
* ML/AI models are Docker containerized and can be deployed horizontally along with the ES database as the volume of queries increases and data volume grows
* Each model has its own endpoints, allowing the front end application developer /users to customize the celebrity recommendations based on different criteria
* Below is an example of the APIs endpoints from this project

![APIs](<../img/List%20of%20NLP-AI-ML%20APIs.png>)

