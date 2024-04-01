<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Statistics</title>
    <link href="../assets/libs/bulma/v1.0.0/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.5.1/css/all.css" rel="stylesheet">
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
        <table class="table is-fullwidth is-narrow sortable">
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

            <div class="field has-addons is-pulled-right">
              <form method="post" class="control">
                <input type="hidden" name="action" value="start">
                <button type="submit" class="button" title="Start service statistics sampling" %ifvar $context/sampling.started? equals('true')%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-play"></i>
                  </span>
                </button>
              </form>
              <form method="post" class="control">
                <input type="hidden" name="action" value="stop">
                <button type="submit" class="button" title="Stop service statistics sampling" %ifvar $context/sampling.started? equals('true')%%else%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-stop"></i>
                  </span>
                </button>
              </form>
              <form method="get" class="control">
                <button type="submit" class="button" title="Refresh service statistics" %ifvar $context/sampling.started? equals('true')%%else%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-rotate-right"></i>
                  </span>
                </button>
              </form>
            </div>
          </caption>

          <thead>
            <tr>
              <th class="unwrappable has-text-left sorttable_sorted" width="37%">
                <span class="icon" title="Service">
                  <i class="fa-solid fa-gear"></i>
                </span>
                <span>
                  Service
                </span>
              </th>
              <th class="unwrappable has-text-right" width="9%">
                <span class="icon" title="Minimum Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Minimum Duration<br><small>(seconds)</small>
                </span>
              </th>
              <th class="unwrappable has-text-right" width="9%">
                <span class="icon" title="Average Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Average Duration<br><small>(seconds)</small>
                </span>
              </th>
              <th class="unwrappable has-text-right" width="9%">
                <span class="icon" title="Standard Deviation Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Standard Deviation<br>Duration<br><small>(seconds)</small>
                </span>
              </th>
              <th class="unwrappable has-text-right" width="9%">
                <span class="icon" title="Maximum Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Maximum Duration<br><small>(seconds)</small>
                </span>
              </th>
              <th class="unwrappable has-text-right" width="9%">
                <span class="icon" title="Total Cumulative Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Total Cumulative<br>Duration<br><small>(seconds)</small>
                </span>
              </th>
              <th class="unwrappable has-text-right" width="6%">
                <span class="icon" title="Successful Execution Count">
                  <i class="fa-solid fa-circle-check"></i>
                </span>
                <span>
                  Successful<br>Execution<br>Count
                </span>
              </th>
              <th class="unwrappable has-text-right" width="6%">
                <span class="icon" title="Failed Execution Count">
                  <i class="fa-solid fa-circle-xmark"></i>
                </span>
                <span>
                  Failed<br>Execution<br>Count
                </span>
              </th>
              <th class="unwrappable has-text-right" width="6%">
                <span class="icon" title="Total Execution Count">
                  <i class="fa-solid fa-circle"></i>
                </span>
                <span>
                  Total<br>Execution<br>Count
                </span>
              </th>
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
