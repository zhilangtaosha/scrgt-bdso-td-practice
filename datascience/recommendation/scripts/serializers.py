from flask_restplus import fields
from app_api import api



genreRec = api.model('Celebrity Recomendation by Genre', {
    'genre': fields.String(description='The unique identifier of a blog post'),
    'match': fields.String(description='Article title'),
    'score': fields.String(description='Article content'),
})

