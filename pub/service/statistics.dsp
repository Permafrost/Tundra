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
                  Service Invocation Statistics sampling <strong>started</strong>.
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
                  Service Invocation Statistics sampling <strong>stopped</strong>.
                </div>
              %onerror%
                <div class="alert alert-danger alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
                </div>
              %endinvoke%
          %endswitch%
          %invoke tundra.support.service.statistics:list%
          <div class="table-responsive">
            <table class="table table-striped">
              <caption>Service Invocation Statistics%ifvar $context/statistics.length -notempty% &mdash; %ifvar $context/statistics.length equals('1')%1 service sampled%else%%value $context/statistics.length encode(xml)% services sampled%endif%%ifvar $context/sampling.start -notempty% since %value $context/sampling.start encode(xml)%%endif%</caption>
              <thead>
                <tr>
                  <th>Service</th>
                  <th class="text-right" width="10%">Minimum Duration<br/><small>(seconds)</small></th>
                  <th class="text-right" width="10%">Average Duration<br/><small>(seconds)</small></th>
                  <th class="text-right" width="10%">Standard Deviation<br/><small>(seconds)</small></th>
                  <th class="text-right" width="10%">Maximum Duration<br/><small>(seconds)</small></th>
                  <th class="text-right" width="10%">Total Cumulative Duration<br/><small>(seconds)</small></th>
                  <th class="text-right" width="10%">Invocation Count</th>
                </tr>
              </thead>
              %ifvar $context/sampling.started? equals('true')%
              <tbody>
              %loop $context/statistics%
                <tr>
                  <td>%value service encode(xml)%</td>
                  <td class="text-right">%value minimum.formatted encode(xml)%</td>
                  <td class="text-right">%value average.formatted encode(xml)%</td>
                  <td class="text-right">%value deviation.standard.formatted encode(xml)%</td>
                  <td class="text-right">%value maximum.formatted encode(xml)%</td>
                  <td class="text-right">%value cumulative.formatted encode(xml)%</td>
                  <td class="text-right">%value count.formatted encode(xml)%</td>
                </tr>
              %endloop%
              </tbody>
              %endif%
              <tfoot>
                <tr>
                  <td colspan="8">
                    <form role="form" class="form-inline" method="post">
                      %ifvar $context/sampling.started? equals('true')%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Stop Sampling</button>
                          <input type="hidden" name="action" value="stop">
                        </div>
                        <div class="form-group">
                          Service Invocation Statistics sampling is currently enabled; to disable click the Stop Sampling button.
                        </div>
                      %else%
                        <div class="form-group">
                          <button type="submit" style="width:100px">Start Sampling</button>
                          <input type="hidden" name="action" value="start">
                        </div>
                        <div class="form-group">
                          Service Invocation Statistics sampling is currently disabled; to enable click the Start Sampling button.
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
