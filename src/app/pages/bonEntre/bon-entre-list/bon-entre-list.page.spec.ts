import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonEntreListPage } from './bon-entre-list.page';

describe('BonEntreListPage', () => {
  let component: BonEntreListPage;
  let fixture: ComponentFixture<BonEntreListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonEntreListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
