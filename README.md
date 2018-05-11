# Google-Domain-Position-Finder

<b>Description: </b>Finds position of given domain in given google query using chosen or default user-agent.
i.e:
- domain = "pl.wikipedia.org"
- keywords = "wiki"
- user-agent (set to default = "Chrome", other user-agents available only through code)

Application should find position of given domain in search query result. For example, if chosen domain is "pl.wikipedia.org" and after searchiing "wiki" in Google this domain is on 4th position, that position will be displayed to the user.

Application uses two strategies to get query results from Google search engine:
 - Google Custom Search API
 - simple URL request (parsing response body)

