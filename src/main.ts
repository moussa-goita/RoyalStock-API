import { bootstrapApplication } from '@angular/platform-browser';
import { RouteReuseStrategy, provideRouter, withPreloading, PreloadAllModules } from '@angular/router';
import { IonicRouteStrategy, provideIonicAngular } from '@ionic/angular/standalone';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { routes } from './app/app.routes';
import { AppComponent } from './app/app.component';

import { defineCustomElements } from '@ionic/pwa-elements/loader';
import { addIcons } from 'ionicons';
import {home, cube, enter, exit, pricetags, key, business, people, person, mail, lockClosed, eye} from 'ionicons/icons';

addIcons({
  'home': home,
  'cube': cube,
  'enter': enter,
  'exit': exit,
  'pricetags': pricetags,
  'key': key,
  'business': business,
  'people': people,
  'person': person,
  'mail': mail,
  'lock-closed': lockClosed,
  'eye': eye,
});

bootstrapApplication(AppComponent, {
  providers: [
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    provideIonicAngular(),
    provideRouter(routes, withPreloading(PreloadAllModules)),
    importProvidersFrom(HttpClientModule)
  ],
}).then(() => {
  defineCustomElements(window);
});
