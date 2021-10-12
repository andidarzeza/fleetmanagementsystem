import {APP_BASE_HREF} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {HttpClientModule, HttpClient, HTTP_INTERCEPTORS} from '@angular/common/http';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';

import {ToastrModule} from 'ngx-toastr';
import {LeafletModule} from '@asymmetrik/ngx-leaflet';
import {LeafletDrawModule} from '@asymmetrik/ngx-leaflet-draw';

import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import 'hammerjs';
import {registerLocaleData} from '@angular/common';
import localeIt from '@angular/common/locales/it';
import {FuseModule} from "@fuse/fuse.module";
import {fuseConfig} from './fuse-config';
import {SharedModule} from "./shared-module/shared.module";
import {CustomHttpLoaderFactory} from "./shared-module/customLanguageHttpLoader";
import {SsoModule} from "../../sso-implementation/sso.module";
import {environment} from "../environments/environment";
import {CookieService} from "ngx-cookie-service";
import {DragDropModule} from "@angular/cdk/drag-drop";
import {NgxAutoScrollModule} from "ngx-auto-scroll";
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {ConfirmDialogComponent} from "./base-layout/components/confirm-dialog/confirm-dialog.component";
import {TokenService} from "./base-layout/services/token.service";
import {VehicledetailsComponent} from "./base-layout/components/vehicledetails/vehicledetails.component";
registerLocaleData(localeIt, 'it');


@NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        DragDropModule,
        MatProgressBarModule,
        NgxAutoScrollModule,
        HttpClientModule,
        AppRoutingModule,
        SharedModule,
        ToastrModule.forRoot( {
            timeOut: 2000,
            positionClass: 'toast-top-right',
            preventDuplicates: false,
            progressBar: true,
            maxOpened: 10,
            tapToDismiss: true,
            newestOnTop: true
        }),
        LeafletModule,
        LeafletModule.forRoot(),
        LeafletDrawModule.forRoot(),
        FuseModule.forRoot(fuseConfig),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: CustomHttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
        SsoModule.forRoot(environment)
    ],
    bootstrap: [AppComponent],
    entryComponents: [ConfirmDialogComponent,
    VehicledetailsComponent],
    providers: [
        CookieService,
        {
        provide: HTTP_INTERCEPTORS,
        useClass: TokenService,
        multi: true
    }],

})
export class AppModule {

}

