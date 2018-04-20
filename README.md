# Elasticsearch Java Client 예제

## 버전
Elasticsearch 6.2.4

## 선행작업
* Elasticsearch 6.2.4 설치
* 동의어 사전 구축(config/synonym.txt) 설치

## 실행결과
* 색인 후 검색 결과 예
~~~JSON
{
    "took": 0,
    "timed_out": false,
    "_shards": {
        "total": 5,
        "successful": 5,
        "skipped": 0,
        "failed": 0
    },
    "hits": {
        "total": 3,
        "max_score": 1,
        "hits": [
            {
                "_index": "shopping",
                "_type": "review",
                "_id": "AvoV4mIBUfH0ovhTlL6p",
                "_score": 1,
                "_source": {
                    "departmentName": "Tops",
                    "title": "Looks great with white pants",
                    "reviewText": "Took a chance on this blouse and so glad i did. i wasn't crazy about how the blouse is photographed on the model. i paired it whit white pants and it worked perfectly. crisp and clean is how i would describe it. launders well. fits great. drape is perfect. wear tucked in or out - can't go wrong."
                }
            },
            {
                "_index": "shopping",
                "_type": "review",
                "_id": "AfoV4mIBUfH0ovhTlL6p",
                "_score": 1,
                "_source": {
                    "departmentName": "Dresses",
                    "title": "Flattering",
                    "reviewText": "I love this dress. i usually get an xs but it runs a little snug in bust so i ordered up a size. very flattering and feminine with the usual retailer flair for style."
                }
            },
            {
                "_index": "shopping",
                "_type": "review",
                "_id": "A_oV4mIBUfH0ovhTlL6p",
                "_score": 1,
                "_source": {
                    "departmentName": "Jackets",
                    "title": "Super cute and cozy",
                    "reviewText": "A flattering, super cozy coat.  will work well for cold, dry days and will look good with jeans or a dressier outfit.  i am 5' 5'', about 135 and the small fits great."
                }
            }
        ]
    }
}
~~~