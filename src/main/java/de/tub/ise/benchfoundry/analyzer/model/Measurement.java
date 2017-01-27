package de.tub.ise.benchfoundry.analyzer.model;

import de.tub.ise.benchfoundry.analyzer.model.enums.ResultType;

import java.util.List;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class Measurement {

    private final String node_id;
    private final Long process_id;
    private final Long transaction_id;
    private final Long operation_id;
    private final Long logical_query_id;
    private final Long target_operation_start;
    //private final List<RequestResult> subrequests; //TODO How do we handel subrequests?
    private final Long is_operation_start;
    private final Long is_operation_end;
    private final ResultType result_type;
    private final List<List<String>> operation_result;

    public Measurement(String node_id, Long process_id, Long transaction_id, Long operation_id, Long logical_query_id, Long target_operation_start, Long is_operation_start, Long is_operation_end, ResultType result_type, List<List<String>> operation_result) {
        this.node_id = node_id;
        this.process_id = process_id;
        this.transaction_id = transaction_id;
        this.operation_id = operation_id;
        this.logical_query_id = logical_query_id;
        this.target_operation_start = target_operation_start;
        this.is_operation_start = is_operation_start;
        this.is_operation_end = is_operation_end;
        this.result_type = result_type;
        this.operation_result = operation_result;
    }

    public String getNode_id() {
        return node_id;
    }

    public Long getProcess_id() {
        return process_id;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public Long getOperation_id() {
        return operation_id;
    }

    public Long getTarget_operation_start() {
        return target_operation_start;
    }

    public Long getLogical_query_id() {
        return logical_query_id;
    }

    public Long getIs_operation_start() {
        return is_operation_start;
    }

    public Long getIs_operation_end() {
        return is_operation_end;
    }

    public ResultType getResult_type() {
        return result_type;
    }

    public List<List<String>> getOperation_result() {
        return operation_result;
    }

    /**
     * Returns the request-response latency of the business operation, i.e., the difference between is_operation_end and is_operation_start.
     * @return request-response latency of the business operation.
     */
    public Long getRequestResponseLatency() { return is_operation_end-is_operation_start; }
}
