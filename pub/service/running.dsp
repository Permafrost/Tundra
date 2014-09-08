<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra&gt;Services Running</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/application.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          %invoke tundra.support.service.execution.ui:get%
          <h4 class="sub-header">Services Currently Running (%value $context/invocations.current.length encode(xml)%)</h4>
          <div class="table-responsive">
            <table class="table" width="100%">
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
                  <th></th>
                </tr>
              </thead>
              <tbody>
              %loop $context/invocations.current%
                <tr>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.id encode(xml)%</td>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.name encode(xml)%</td>
                  %loop callstack -$index%
                  <td>%value package encode(xml)%</td>
                  <td>%value service encode(xml)%</td>
                  <td>%value start encode(xml)%</td>
                  <td>%value duration encode(xml)%</td>
                  <td>
                    <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%">
                      <span class="glyphicon glyphicon-list"> Properties</span>
                    </button>
                    <!-- Modal -->
                    <div class="modal" id="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" tabindex="-1" role="dialog">
                      <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span>&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">Properties</h4>
                          </div>
                          <div class="modal-body">
                            <table class="table table-bordered">
                              <tbody>
                                <tr>
                                  <th>Session</th>
                                  <td>%value session encode(xml)%</td>
                                </tr>
                                <tr>
                                  <th>User</th>
                                  <td>%value user encode(xml)%</td>
                                </tr>
                               <tr>
                                  <th>Pipeline</th>
                                  <td>
                                    %ifvar pipeline.length equals('0')%
                                    &mdash;
                                    %else%
                                    <table class="table table-bordered table-fixed">
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
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                      </div>
                    </div>
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
