# BenchFoundry Analyzer
Analyzer for results obtained by the BenchFoundry benchmarking framework.

## Goal

The main goal of this project is to provide an extensible framework to extract meaningful aggregates for raw measurements obtained after running a benchmark with the BenchFoundry execution framework. In order to achieve this goal, we must consider a set of specific design requirements.

## Background
The workload abstraction of the BenchFoundry execution framework includes a hierarchy: BusinessProcess > BusinessTransaction > BusinessOperation > Request. A workload can be executed by multiple BenchFoundry clients in parallel. The granularity of distribution is a BusinessProcess, thus, all subunits of a BusinessProcess are executed by the same BenchFoundry client.
After running a benchmark with the BenchFoundry execution framework, each BenchFoundry client produces a single output file that contains the raw measurements for this client. Each measurement is stored in a specific data format. Depending on the workload, result files can become large > 1GB. Therefore, the structure is implicit to minimize storage requirements.

## Requirements
- **R1**: Accept a set of files with raw measurements from the BenchFoundry execution framework as input to the BenchFoundry analyzer.
- **R2**: Preserve meta-information provided by the implicit structure of the raw result files provided by the BenchFoundry execution framework.
- **R3**: Scale to large input files that exceed multiple GB in total size.
- **R4**: Avoid high resource requirements for a target execution environment to run the BenchFoundry analyzer.
- **R5**: Provide functionality to derive common statistics, e.g., min, max, count, arithmetic mean, percentiles.
- **R6**: Allow for easy extension of the BenchFoundry analyzer in terms of additional aggregates and metrics.
- **R7**: Allow for easy extension of the BenchFoundry analyzer in terms of additional input types.
- **R8**: Allow rendering aggregates on the console or in a result file according to a customizable style sheet.
- **R9**: Develop against JDK 8uxxx to avoid additional deployment requirements over the benchFoundry execution framework.
- **R10**: Allow for explicit strategies to handle any corrupt or missing data.

## Design
#### Canonical Data Format
The BenchFoundry analyzer maps raw measurements internally in a canonical data format that can be represented as a relational schema (R1 & R2). Each row in the corresponding relation represents a single BusinessOperation executed by the BenchFoundry execution framework. We refer to a single row as Measurement. The relation includes the following attributes:

- **executing_node_id**: The identifier of the BenchFoundry client that executed the corresponding BusinessProcess. 
- **process_id**: The identifier of the corresponding BusinessProcess.
- **transaction_id**: The identifier of the corresponding BusinessTransaction.
- **operation_id**: The identifier of the corresponding BusinessOperation.
- **target_operation_start**: The planned scheduling time for the BusinessOperation.
- **subrequests**: The requests derived from execution of the logical BusinessOperation against a physical schema.
- **loqical_query_id**: ?
- **is_operation_start**: The actual point in time when the corresponding BusinessOperation has been issued by a BenchFoundry client against an SUT.
- **is_operation_end**: The actual point in time when a BenchFoundry client received a response from an SUT for the corresponding BusinessOperation.
- **result_type**: Indicates if the response contains any errors.
- **operation_result**: The actual result provided in the response.

We assume that this canonical data format is not exhaustive. Therefore, we allow adding additional attributes in the future (R7).

#### Stream Processing Core
We designed the core of the BenchFoundry analyzer as stream processing application to support raw inputs that exceed the size of the memory in a target execution environment (R3 & R4). The stream processing core operates on a stream of Measurement elements that is provided by a StreamBuilder. The Processor is initialized with a set of MetricProvider objects. A MetricProvider operates on a stream of Measurement elements and returns an unordered list of Metric objects. The Processor wraps all Metric objects provided by all MetricProvider objects in a single Statistics that is forwarded to a Renderer. The renderer prints raw Metrics or populates a template with metrics provided by a Statistics object. 
