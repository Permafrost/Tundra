<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Statistics</title>
    <link href="../assets/libs/bulma/v0.9.4/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.4.0/css/all.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/libs/sorttable/v2/js/sorttable.js"></script>
    <script src="../assets/js/application.js"></script>
  </head>
  <body>
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
      %switch action%
        %case 'start'%
          %invoke tundra.support.service.statistics:start%
          %onerror%
          <div class="message is-danger">
            <div class="message-header">
              <p>Error</p>
              <button class="delete"></button>
            </div>
            <div class="message-body">
               %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          </div>
          %endinvoke%

        %case 'stop'%
          %invoke tundra.support.service.statistics:stop%
          %onerror%
          <div class="message is-danger">
            <div class="message-header">
              <p>Error</p>
              <button class="delete"></button>
            </div>
            <div class="message-body">
               %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          </div>
          %endinvoke%
      %endswitch%

      %invoke tundra.support.service.statistics:list%
        <table class="table is-bordered is-striped is-fullwidth is-hoverable is-narrow sortable">
          <caption>
            <span class="is-pulled-left">
              Tundra &gt; Service Usage Statistics%ifvar $context/statistics.length -notempty%
              %ifvar $context/sampling.started? equals('true')%
                &mdash;
                %value $context/statistics.length encode(xml)%
                %ifvar $context/statistics.length equals('1')%
                  service
                %else%
                  services
                %endif%
                sampled
                %ifvar $context/sampling.start -notempty%
                  since %value $context/sampling.start encode(xml)%
                %endif%
              %endif%
            </span>

            <div class="field is-grouped is-pulled-right">
              <form role="form" class="" method="post">
                %ifvar $context/sampling.started? equals('true')%
                  <div class="form-group">
                    <input type="hidden" name="action" value="stop">
                    <button type="submit" class="button is-warning ml-2" title="Pause service statistics sampling">
                      <span class="icon">
                        <i class="fas fa-regular fa-pause"></i>
                      </span>
                    </button>
                  </div>
                %else%
                  <div class="form-group">
                    <input type="hidden" name="action" value="start">
                    <button type="submit" class="button is-success ml-2" title="Start service statistics sampling">
                      <span class="icon">
                        <i class="fas fa-solid fa-play"></i>
                      </span>
                    </button>
                  </div>
                %endif%
              </form>

              <span class="">
                <a href="#" class="button ml-2" onClick="window.location.reload();" title="Refresh service statistics">
                  <span class="icon">
                    <i class="fas fa-solid fa-rotate-right"></i>
                  </span>
                </a>
              </span>
            </div>
          </caption>

          <thead>
            <tr>
              <th class="has-text-left sorttable_sorted" width="35%">Service</th>
              <th class="has-text-right" width="10%">Minimum Duration<br/><small>(seconds)</small></th>
              <th class="has-text-right" width="10%">Average Duration<br/><small>(seconds)</small></th>
              <th class="has-text-right" width="10%">Standard Deviation<br/><small>(seconds)</small></th>
              <th class="has-text-right" width="10%">Maximum Duration<br/><small>(seconds)</small></th>
              <th class="has-text-right" width="10%">Total Cumulative Duration<br/><small>(seconds)</small></th>
              <th class="has-text-right" width="5%">Successful Execution Count</th>
              <th class="has-text-right" width="5%">Failed Execution Count</th>
              <th class="has-text-right" width="5%">Total Execution Count</th>
            </tr>
          </thead>

          %ifvar $context/sampling.started? equals('true')%
          <tbody>
          %loop $context/statistics%
            <tr>
              <td>%value subject encode(xml)%</td>
              <td class="has-text-right">%value minimum.formatted encode(xml)%</td>
              <td class="has-text-right">%value average.formatted encode(xml)%</td>
              <td class="has-text-right">%value deviation.standard.formatted encode(xml)%</td>
              <td class="has-text-right">%value maximum.formatted encode(xml)%</td>
              <td class="has-text-right">%value cumulative.formatted encode(xml)%</td>
              <td class="has-text-right">%value count.successes.formatted encode(xml)%</td>
              <td class="has-text-right">%value count.failures.formatted encode(xml)%</td>
              <td class="has-text-right">%value count.total.formatted encode(xml)%</td>
            </tr>
          %endloop%
          </tbody>
          %endif%
        </table>
      </div>
      %endinvoke%

      </div>
    </section>
  </body>
</html>
