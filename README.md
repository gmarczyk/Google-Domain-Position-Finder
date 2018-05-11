# Google-Domain-Position-Finder
<b>Description: </b>Application finds position of given domain in given google query, using default user-agent or the one chosen by user. Simply gets the SERP (Search Engine Results Page) and searches for input domain to determine it's position. Provides ability to choose  strategy of getting query results (SERP) from Google search engine:

 - Google Custom Search API
 - simple URL request (parsing response html body)

Also, simple URL request strategy was designed to ignore PPC (pay per click) results (advertised ones).

<b>Example of usage:</b> if chosen domain is "pl.wikipedia.org" and after searchiing "wiki" in Google this domain is on 4th position, that position will be displayed to the user. One can check where would be any site (domain) positioned after searching in Google.

- domain = "pl.wikipedia.org"
- keywords = "wiki"
- user-agent (set to default = "Chrome", other user-agents available only through code)
