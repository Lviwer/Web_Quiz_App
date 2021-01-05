#### Web Quiz App 

This is a Spring Boot API which will serve as a web quiz.

* The client can create their own quizzes and send them to
the server. ("/api/quizzes")

* The client can request all his quizzess.("/api/quizzes")
          <p>API return only 10 quizzes at once and supports the ability to specify which portion of quizzes is needed.</p>
          <p>The response contains a JSON with quizzes (inside <code class="java">content</code>) and some additional metadata:</p>
          <pre><code class="java">{
            "totalPages":1,
            "totalElements":3,
            "last":true,
            "first":true,
            "sort":{ },
            "number":0,
            "numberOfElements":3,
            "size":10,
            "empty":false,
            "pageable": { },
            "content":[
              {"id":102,"title":"Test 1","text":"Text 1","options":["a","b","c"]},
              {"id":103,"title":"Test 2","text":"Text 2","options":["a", "b", "c", "d"]},
              {"id":202,"title":"The Java Logo","text":"What is depicted on the Java logo?",
               "options":["Robot","Tea leaf","Cup of coffee","Bug"]}
            ]
}</code></pre>

   <p>The API support the navigation through pages by passing the <code class="java">page</code> parameter (<code class="java">/api/quizzes?page=1</code>).
   The first page is 0 since pages start from zero.</p>
   <p>If there are no quizzes, <code class="java">content</code> is empty <code class="java">[]</code>. 
    If the user is authorized, the status code is <code class="java">200 (OK)</code>; otherwise, it's <code class="java">401 (Unauthorized)</code>.</p>



* The client can request a quiz by ID - authorization required. ("api/quizzes/{id}") 


* The client can solve the quiz by passing the ID into the ("/api/quizzes/{id}/solve") and the answer 
   as a query string parameter, `?answer=2`. A response will be sent to the client giving feedback as to  the result
   of their answer, correct or incorrect.
   
 
* The client can request only his solved quizzes - authorization required. ("api/quizzes/completed") 
  It is allowed to solve a quiz multiple times, the response may contain duplicate quizzes, but with the different completion date.</p>


 
* The client can delete his specific quiz - authorization required. ("/api/quizzes/{id}") 


* To create new client use unique parameters with ("/api/register") address.

