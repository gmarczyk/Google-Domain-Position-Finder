# Google-Query-Position-Searcher
<b>Description: </b>Finds position of given domain in given google query using chosen or given user-agent.
i.e:
- domain = "pl.wikipedia.org"
- keywords = "wiki"
- user-agent = "Chrome"

Application should find position of the recrd of query result. If pl.wikipedia.org after searching "wiki" in Google search engine is on 4th position, such position will be displayed to the user.

<b>IMPORTANT:</b> Some of user-agents give no results. This happens because of different structure of html content received as a result of request. Application does support only one specific structure and in addition it doesn't take into account advertisements, "recommended" etc.
Also not all user-agents available to choose in application are working. Some of them are deprecated or not supported by google search engine.

Repository files are IntelliJ IDEA 2017.1 project.
Code is located in folders: 
- src 
- test

Executable can be found in the path:
- out\artifacts\GoogleQueryPositionSearcher_jar

