import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './components/search.component';
import { MarvelService } from './marvel.service';
import { CharacterComponent } from './components/character.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    CharacterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ MarvelService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
