// query handler code
@Component
public class BasicQueryListener {   
    @QueryHandler
    public BasicQueryResponse on(BasicQuery query) {
        return "hello world " + query;
    }
}

// client-code
@RestController
public class QueryHttpController {
    @Inject private QueryGateway queryGateway;

    @GetMapping("/query")
    public BasicResponse(@RequestParam("q") String query) {
        return queryGateway
        .scatterGather(new BasicQuery(query), instanceOf(BasicQueryResponse.class), 30, TimeUnit.SECONDS)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
    }
}