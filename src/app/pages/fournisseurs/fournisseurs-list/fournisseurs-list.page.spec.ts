import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FournisseursListPage } from './fournisseurs-list.page';

describe('FournisseursListPage', () => {
  let component: FournisseursListPage;
  let fixture: ComponentFixture<FournisseursListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(FournisseursListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
