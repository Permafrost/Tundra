<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service &gt; Execution</title>
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/Tundra/service/execution.dsp">Tundra &gt; Service &gt; Execution</a>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          %invoke tundra.support.service.execution:get%
          <h4 class="sub-header">Services Currently Executing (%value $context/invocations.current.length encode(xml)%)</h4>
          <div class="table-responsive">
            <table class="table table-striped" width="100%">
              <thead>
                <tr>
                  <th colspan="2">Thread</th>
                  <th colspan="5">Call Stack</th>
                </tr>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Package</th>
                  <th>Service</th>
                  <th>Start Time</th>
                  <th>Duration</th>
                  <th>Pipeline</th>
                </tr>
              </thead>
              <tbody>
              %loop $context/invocations.current%
                <tr>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.id encode(xml)%</td>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.name encode(xml)%</td>
                  %loop callstack%
                  <td>%value package encode(xml)%</td>
                  <td>%value service encode(xml)%</td>
                  <td>%value start encode(xml)%</td>
                  <td>%value duration encode(xml)%</td>
                  <td>
                    %ifvar pipeline.length equals('0')%
                    &mdash;
                    %else%
                    <table class="table table-bordered">
                      <tbody>
                        %loop pipeline -struct%
                        <tr>
                          <th>%value $key encode(xml)%</th>
                          <td>%value encode(xml)%</td>
                        </tr>
                        %endloop%
                      </tbody>
                    </table>
                    %endif%
                  </td>
                  %loopsep '</tr><tr>'%
                  %endloop%
                </tr>
              %endloop%
              </tbody>
            </table>
          </div>
          %endinvoke%
        </div>
      </div>
    </div>

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
