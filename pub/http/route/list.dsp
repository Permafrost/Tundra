<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; HTTP Route</title>
    <link href="../../assets/libs/bulma/v0.9.4/css/bulma.min.css" rel="stylesheet">
    <link href="../../assets/libs/fontawesome/v6.4.0/css/all.css" rel="stylesheet">
    <link href="../../assets/css/application.css" rel="stylesheet">
    <script src="../../assets/js/application.js"></script>
  </head>
  <body>
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
      %ifvar action equals('refresh')%
        %invoke tundra.http.route:refresh%
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
      %endif%

      %invoke tundra.http.route:list%
        <table class="table is-fullwidth is-narrow">
          <caption class="">
            <span class="is-pulled-left">
              Tundra &gt; HTTP Route
            </span>

            <div class="field is-grouped is-pulled-right">
              <form role="form" method="post">
                <div class="form-group">
                  <input type="hidden" name="action" value="refresh">
                  <button type="submit" class="button ml-2" title="Refresh all HTTP routes from server-specific and package-specific http-routes.cnf configuration files">
                    <span class="icon">
                      <i class="fas fa-solid fa-rotate-right"></i>
                    </span>
                  </button>
                </div>
              </form>
            </div>
          </caption>

          <thead>
            <tr>
              <th class="unwrappable">
                <span class="icon" title="Directive">
                  <i class="fa-solid fa-diamond-turn-right"></i>
                </span>
                <span>
                  Directive
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="HTTP Method">
                  <i class="fa-solid fa-paper-plane"></i>
                </span>
                <span>
                  HTTP Method
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="URI Template">
                  <i class="fa-solid fa-folder-tree"></i>
                </span>
                <span>
                  URI Template
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Target">
                  <i class="fa-solid fa-gear"></i>
                </span>
                <span>
                  Target
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Description">
                  <i class="fa-solid fa-bars"></i>
                </span>
                <span>
                  Description
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Source">
                  <i class="fa-solid fa-file-code"></i>
                </span>
                <span>
                  Source
                </span>
              </th>
            </tr>
          </thead>

          <tbody>
          %ifvar $routes.length equals('0')%
            <tr class="example">
              <td>example</td>
              <td>get</td>
              <td>/example/{foo}/{bar}</td>
              <td>example.foo.bar:get</td>
              <td>
                <p>This is an <strong>example custom HTTP route</strong> which would invoke
                the <code>example.foo.bar:get</code> service for any HTTP GET request where
                the request URI matches the template <code>/example/{foo}/{bar}</code>, where
                <code>{foo}</code> and <code>{bar}</code> are variables that can match any
                valid URI path segment.</p>

                <p>Custom HTTP routes specify an <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">
                HTTP request method</a> and <a href="https://tools.ietf.org/html/rfc6570">URI
                template</a> which, if matched by an Integration Server HTTP request,
                will return the associated target (either by invoking the target if
                it is a service in the same way as the built-in <code>/invoke</code> URIs work,
                or by returning a DSP page or static asset if the target is not a
                service), and can be used to abstract the API used by HTTP clients
                from the implementation (unlike <code>/invoke</code> URIs or package DSP pages
                URIs, which leak the implementing service in the URI's path).</p>

                <p>Custom HTTP routes can either be configured in a server-specific
                configuration file, or a package-specific configuration file.</p>

                <p>To register a server-specific custom HTTP route, create or edit the
                <code>&lt;IntegrationServer&gt;/config/http-routes.cnf</code> file, using the
                <code>&lt;IntegrationServer&gt;/packages/Tundra/config/http-routes.example.cnf</code>
                file as a template.</p>

                <p>To register a package-specific custom HTTP route, create or edit the
                <code>&lt;IntegrationServer&gt;/packages/&lt;PackageName&gt;/config/http-routes.cnf</code>
                file, using the
                <code>&lt;IntegrationServer&gt;/packages/Tundra/config/http-routes.example.cnf</code>
                file as a template.</p>

                <p>Note that server-specific HTTP routes take precedence over package-
                specific routes, and package-specific routes are loaded in lexical
                package name order. Routing instructions inside each configuration
                file must be specified in order of precedence, and all routes are
                aggregated into a single routing table in Integration Server, and
                therefore care must be taken so that routes specified across the
                various configuration files do not override each other.<p>

                <p>Changes to these HTTP route configuration files do not take effect
                until <code>Tundra/tundra.http.route:refresh</code> is invoked, either manually,
                by reloading the Tundra package, or by clicking the Refresh button
                on this page.</p>
              </td>
              <td>Path to source file the route was defined in</td>
            </tr>
          %else%
            %loop $routes -$index%
              <tr>
                <td rowspan="%value routes.length encode(xml)%">%value directive encode(xml)%</td>
                %loop routes -$index%
                <td>%value method encode(xml)%</td>
                <td>%value uri encode(xml)%</td>
                <td>%value target encode(xml)%</td>
                <td>
                %ifvar description -notempty%
                  %value description encode(xml)%
                %else%
                  &ndash;
                %endif%
                </td>
                <td>%value source encode(xml)%</td>
                %loopsep '</tr><tr>'%
                %endloop%
              </tr>
            %endloop%
          %endif%
          </tbody>
        </table>

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
      </div>
    </section>
  </body>
</html>
