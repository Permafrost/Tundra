<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Statistics</title>
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
              %invoke tundra.support.service.statistics:start%
                <div class="alert alert-success alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  Service statistics collection <strong>started</strong>.
                </div>
              %onerror%
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
                </div>
              %endinvoke%
            %case 'stop'%
              %invoke tundra.support.service.statistics:stop%
                <div class="alert alert-warning alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  Service statistics collection <strong>stopped</strong>.
                </div>
              %onerror%
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
                </div>
              %endinvoke%
          %endswitch%
          %invoke tundra.support.service.statistics.ui:list%
          <div class="table-responsive">
            <table class="table table-striped">
              <caption>Service Execution Duration Statistics (measured in seconds)</caption>
              <thead>
                <tr>
                  <th>Service</th>
                  <th class="text-right" width="20%">Average &plusmn; Standard Deviation</th>
                  <th class="text-right" width="10%">Minimum</th>
                  <th class="text-right" width="10%">Maximum</th>
                  <th class="text-right" width="10%">Count</th>
                </tr>
              </thead>
              %ifvar $context/collection.started? equals('true')%
              <tbody>
              %loop $context/statistics%
                <tr>
                  <td>%value service encode(xml)%</td>
                  <td class="text-right">%value mean encode(xml)%&nbsp;&plusmn;&nbsp;%value stdev encode(xml)%</td>
                  <td class="text-right">%value minimum encode(xml)%</td>
                  <td class="text-right">%value maximum encode(xml)%</td>
                  <td class="text-right">%value count encode(xml)%</td>
                </tr>
              %endloop%
              </tbody>
              %endif%
              <tfoot>
                <tr>
                  <td colspan="8">
                    <form role="form" class="form-inline" method="post">
                      %ifvar $context/collection.started? equals('true')%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Stop Collection</button>
                          <input type="hidden" name="action" value="stop">
                        </div>
                        <div class="form-group">
                          Service statistics collection is currently enabled; to disable click the Stop Collection button.
                        </div>
                      %else%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Start Collection</button>
                          <input type="hidden" name="action" value="start">
                        </div>
                        <div class="form-group">
                          Service statistics collection is currently disabled; to enable click the Start Collection button.
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
