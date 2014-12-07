<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Usage</title>
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          %switch action%
            %case 'start'%
              %invoke tundra.support.service.usage:start%
                <div class="alert alert-success alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  Service usage monitoring <strong>started</strong>.
                </div>
              %onerror%
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
                </div>
              %endinvoke%
            %case 'stop'%
              %invoke tundra.support.service.usage:stop%
                <div class="alert alert-warning alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  Service usage monitoring <strong>stopped</strong>.
                </div>
              %onerror%
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
                </div>
              %endinvoke%
          %endswitch%
          %invoke tundra.support.service.usage.ui:get%
          <div class="table-responsive">
            <table class="table table-striped" width="100%">
              <caption>Services Currently Running (%value $context/invocations.current.length encode(xml)%)</caption>
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
                  <th>&hellip;</th>
                </tr>
              </thead>
              %ifvar $context/monitoring.started? equals('true')%
              <tbody>
              %loop $context/invocations.current%
                <tr>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.id encode(xml)%</td>
                  <td rowspan="%value callstack.length encode(xml)%">%value thread.name encode(xml)%</td>
                  %loop callstack -$index%

                  <td>%value package encode(xml)%</td>
                  <td>
                    %value service encode(xml)%
                  </td>
                  <td>%value start encode(xml)%</td>
                  <td>%value duration encode(xml)%</td>
                  <td width="15">
                    <a href="#" data-toggle="modal" data-target="#properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%">
                      <img src="../assets/icons/page_white_magnify.png">
                    </a>
                    <!-- Modal -->
                    <div class="modal" id="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" tabindex="-1" role="dialog">
                      <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Properties</h4>
                          </div>
                          <div class="modal-body">
                            <table class="table table-striped">
                              <thead>
                                <tr>
                                  <th>Property</th>
                                  <th>Value</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <th>Service</th>
                                  <td>%value service encode(xml)%</td>
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
                                    <table class="table table-striped">
                                      <thead>
                                        <tr>
                                          <th>Key</th>
                                          <th>Value</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        %loop pipeline -struct%
                                        <tr>
                                          <th width="50">%value $key encode(xml)%</th>
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
              %endif%
              <tfoot>
                <tr>
                  <td colspan="7">
                    <form role="form" class="form-inline" method="post">
                      <div class="form-group">
                        %ifvar $context/monitoring.started? equals('true')%
                        <button type="submit" style="width:100px">Stop Monitoring</button>
                        <input type="hidden" name="action" value="stop">
                        %else%
                        <button type="submit" style="width:100px">Start Monitoring</button>
                        <input type="hidden" name="action" value="start">
                        %endif%
                      </div>
                    </form>
                  </td>
                </tr>
              </tfoot>
            </table>
          </div>
          %endinvoke%
        </div>
      </div>
    </div>

    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
  </body>
</html>
