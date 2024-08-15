import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FournisseursEspacePage } from './fournisseurs-espace.page';

describe('FournisseursEspacePage', () => {
  let component: FournisseursEspacePage;
  let fixture: ComponentFixture<FournisseursEspacePage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(FournisseursEspacePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
