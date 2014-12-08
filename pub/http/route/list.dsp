<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; HTTP Routes</title>
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/css/application.css" rel="stylesheet">
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/js/bootstrap.min.js"></script>
    <script src="../../assets/js/application.js"></script>    
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          %ifvar action equals('refresh')%
            %invoke tundra.http.route:refresh%
              <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Success:</strong> All HTTP routes were reloaded successfully.
              </div>
            %onerror%
              <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
              </div>
            %endinvoke%
          %endif%
          %invoke tundra.http.route:list%
          <div class="table-responsive">
            <table class="table table-striped" width="100%">
              <caption>Tundra &gt; HTTP Routes</caption>
              <thead>
                <tr>
                  <th>Directive</th>
                  <th>HTTP Method</th>
                  <th>URI Template</th>
                  <th>Service</th>
                  <th>Description</th>
                  <th>Source</th>
                </tr>
              </thead>
              <tbody>
              %ifvar $routes.length equals('0')%
                <tr>
                  <td>example</td>
                  <td>get</td>
                  <td>/example/{foo}/{bar}</td>
                  <td>example.foo.bar:get</td>
                  <td>
                    <p>This is an <strong>example custom HTTP route</strong> which would invoke the <code>example.foo.bar:get</code> service for any HTTP GET request where the request URI matches the template <code>/example/{foo}/{bar}</code>, where <code>{foo}</code> and <code>{bar}</code> are variables that can match any valid URI path segment.</p>

                    <p>Custom HTTP routes specify an <a href="<http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">HTTP request method</a> and <a href="https://tools.ietf.org/html/rfc6570">URI
                    template</a> which, if matched by an Integration Server HTTP request,
                    will invoke an associated service (in the same way as the built-in
                    <code>/invoke</code> URIs work), and can be used to abstract the API used by HTTP
                    clients from the implementation (unlike <code>/invoke</code> URIs, which leak the
                    implementing service in the URI's path).</p>

                    <p>Custom HTTP routes can either be configured in a server-specific
                    configuration file, or a package-specific configuration file.</p>

                    <p>To register a server-specific custom HTTP route, create or edit the
                    <code>&lt;IntegrationServer&gt;/config/http-routes.cnf</code> file, using the
                    <code>&lt;IntegrationServer&gt;/package/Tundra/config/http-routes.example.cnf</code>
                    file as a template.</p>

                    <p>To register a package-specific custom HTTP route, create or edit the
                    <code>&lt;IntegrationServer&gt;/packages/&lt;PackageName&gt;/config/http-routes.cnf</code>
                    file, using the
                    <code>&lt;IntegrationServer&gt;/package/Tundra/config/http-routes.example.cnf</code>
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
                  <td>&mdash;</td>
                </tr>
              %else%
                %loop $routes -$index%
                  <tr>
                    <td rowspan="%value routes.length encode(xml)%">%value directive encode(xml)%</td>
                    %loop routes -$index%
                    <td>%value method encode(xml)%</td>
                    <td>%value uri encode(xml)%</td>
                    <td>%value service encode(xml)%</td>
                    <td>
                    %ifvar description -notempty%
                      %value description encode(xml)%
                    %else%
                      &mdash;
                    %endif%
                    </td>
                    <td>%value source encode(xml)%</td>
                    %loopsep '</tr><tr>'%
                    %endloop%
                  </tr>
                %endloop%
              %endif%
              </tbody>
              <tfoot>
                <tr>
                  <td colspan="6">
                    <form role="form" class="form-inline" method="post">
                      <div class="form-group">
                        <button type="submit" style="width:100px"><img src="../../assets/icons/arrow_refresh.png">&nbsp;&nbsp;Refresh</button>
                        <input type="hidden" name="action" value="refresh">
                      </div>
                      <div class="form-group">
                        &nbsp;Reload all HTTP routes from server-specific and package-specific <code>http-routes.cnf</code> configuration files.
                      </div>
                    </form>
                  </td>
                </tr>
              </tfoot>
            </table>
          </div>
          %onerror%
            <div class="alert alert-danger alert-dismissible" role="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          %endinvoke%
        </div>
      </div>
    </div>
  </body>
</html>
