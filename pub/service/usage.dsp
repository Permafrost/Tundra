<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Usage</title>
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/application.js"></script>
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
          %invoke tundra.support.service.usage:get%
          <div class="table-responsive">
            <table class="table table-striped">
              <caption>Services Currently Executing (%value $context/invocations.current.length encode(xml)%) at %value $context/monitoring.datetime encode(xml)%</caption>
              <thead>
                <tr>
                  <th width="5%">Thread ID</th>
                  <th width="20%">Thread Name</th>
                  <th>Package</th>
                  <th>Service</th>
                  <th>User</th>
                  <th>Start Time</th>
                  <th>Duration</th>
                  <th><img src="../assets/icons/page_white.png" alt="Input Pipeline..."></th>
                </tr>
              </thead>
              %ifvar $context/monitoring.started? equals('true')%
              %loop $context/invocations.current%
              <tbody>
                <tr class="rowspan">
                  <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%">%value thread.id encode(xml)%</td>
                  <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%">%value thread.name encode(xml)%</td>
                  %loop callstack -$index%
                  <td>%value package encode(xml)%</td>
                  <td>%value service encode(xml)%</td>
                  <td>%value user encode(xml)%</td>
                  <td>%value start encode(xml)%</td>
                  <td>%value duration encode(xml)%</td>
                  <td width="1%">
                    %ifvar pipeline.length equals('0')%
                    &empty;
                    %else%
                    <a href="#" data-toggle="modal" data-target="#properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%">
                      <img src="../assets/icons/page_white_magnify.png">
                    </a>
                    <!-- Modal -->
                    <div class="modal" id="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" tabindex="-1" role="dialog">
                      <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Input Pipeline</h4>
                          </div>
                          <div class="modal-body">
                            %value none pipeline.html encode(none)%
                          </div>
                        </div>
                      </div>
                    </div>
                    %endif%
                  </td>
                  %loopsep '</tr><tr>'%
                  %endloop%
                </tr>
              </tbody>
              %endloop%
              %endif%
              <tfoot>
                <tr>
                  <td colspan="8">
                    <form role="form" class="form-inline" method="post">
                      %ifvar $context/monitoring.started? equals('true')%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Stop Monitoring</button>
                          <input type="hidden" name="action" value="stop">
                        </div>
                        <div class="form-group">
                          Service usage monitoring is currently enabled; to disable click the Stop Monitoring button.
                        </div>
                      %else%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Start Monitoring</button>
                          <input type="hidden" name="action" value="start">
                        </div>
                        <div class="form-group">
                          Service usage monitoring is currently disabled; to enable click the Start Monitoring button.
                        </div>
                      %endif%
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
  </body>
</html>
