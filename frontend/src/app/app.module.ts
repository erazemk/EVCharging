import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {UserComponent} from './seznami/user.component';
import {ChargeAddComponent} from './seznami/charge-add.component';
import {UserDetailsComponent} from './seznami/user-details.component';
import {UserService} from './seznami/services/user.service';


@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        UserComponent,
        UserDetailsComponent,
        ChargeAddComponent
    ],
    providers: [UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

